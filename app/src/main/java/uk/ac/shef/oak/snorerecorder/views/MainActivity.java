package uk.ac.shef.oak.snorerecorder.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import uk.ac.shef.oak.snorerecorder.R;
import uk.ac.shef.oak.snorerecorder.adapters.RecordingElementAdapter;
import uk.ac.shef.oak.snorerecorder.models.RecordingEntity;
import uk.ac.shef.oak.snorerecorder.viewmodels.RecordingViewModel;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_RECORDING_REQUEST = 1;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecordingViewModel mRecordingViewModel;
    private TextView textView;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * Tell application where the view is
         */
        mRecyclerView = findViewById(R.id.mainRecyclerView);

        /**
         * LayoutManager
         */
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        /**
         * Adapter
         */
        final RecordingElementAdapter adapter = new RecordingElementAdapter();
        mRecyclerView.setAdapter(adapter);

        /**
         * assign ViewModel
         */
        mRecordingViewModel = new RecordingViewModel(getApplication());
        mRecordingViewModel.getAllRecordings().observe(this, new Observer<List<RecordingEntity>>() {
            @Override
            public void onChanged(List<RecordingEntity> recordingEntities) {
                adapter.setRecordingEntities(recordingEntities);

                /**
                 * When no recording in the database, text appear
                 */
                textView = findViewById(R.id.noRecordingTextView);
                if(recordingEntities.isEmpty()){
                  textView.setVisibility(View.VISIBLE);
                } else {
                    textView.setVisibility((View.GONE));
                }
            }
        });

        /**
         * take care of swipe to delete
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                swipe(adapter, (RecordingElementAdapter.ViewHolder) viewHolder);
            }
        }).attachToRecyclerView(mRecyclerView);

        /**
         * fab action
         */
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecordingActivity.class);
                startActivityForResult(intent,ADD_RECORDING_REQUEST);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:{
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.action_help: {
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.action_about: {
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.action_deleteAll: {
                deleteAll();
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_RECORDING_REQUEST && resultCode == RESULT_OK) {
            String date = data.getStringExtra(RecordingActivity.EXTRA_DATE);
            String startTime = data.getStringExtra(RecordingActivity.EXTRA_START_TIME);
            String stopTime = data.getStringExtra(RecordingActivity.EXTRA_STOP_TIME);

            RecordingEntity recordingEntity = new RecordingEntity(date,startTime+"-"+stopTime);
            mRecordingViewModel.insert(recordingEntity);
        }
    }

    /**
     * Take care of swipe action
     * @param adapter
     * @param viewHolder
     */
    public void swipe(final RecordingElementAdapter adapter, final RecordingElementAdapter.ViewHolder viewHolder) {
        AlertDialog.Builder deleteAlertBuilder = new AlertDialog.Builder(MainActivity.this);
        deleteAlertBuilder.setTitle(R.string.delete_alert_title);
        deleteAlertBuilder.setMessage(R.string.delete_alert_message);
        deleteAlertBuilder.setCancelable(true);
        deleteAlertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mRecordingViewModel.delete(adapter.getRecordingEntityAt(viewHolder.getAdapterPosition()));
            }
        });
        deleteAlertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mRecordingViewModel.update(adapter.getRecordingEntityAt(viewHolder.getAdapterPosition()));
                dialog.cancel();
            }
        });
        deleteAlertBuilder.setIcon(R.drawable.ic_warning);
        AlertDialog deleteAlertDialog = deleteAlertBuilder.create();
        deleteAlertDialog.show();
    }

    /**
     * Function for delete all recordings
     */
    public void deleteAll() {
        AlertDialog.Builder deleteAlertBuilder = new AlertDialog.Builder(MainActivity.this);
        deleteAlertBuilder.setTitle(R.string.delete_alert_title);
        deleteAlertBuilder.setMessage(R.string.delete_alert_message);
        deleteAlertBuilder.setCancelable(true);
        deleteAlertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mRecordingViewModel.deleteAllNotes();
            }
        });
        deleteAlertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        deleteAlertBuilder.setIcon(R.drawable.ic_warning);
        AlertDialog deleteAlertDialog = deleteAlertBuilder.create();
        deleteAlertDialog.show();
    }
}
