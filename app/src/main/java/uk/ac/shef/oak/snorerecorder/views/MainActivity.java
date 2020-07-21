package uk.ac.shef.oak.snorerecorder.views;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import uk.ac.shef.oak.snorerecorder.R;
import uk.ac.shef.oak.snorerecorder.adapters.RecordingElementAdapter;
import uk.ac.shef.oak.snorerecorder.models.RecordingElement;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    /**
     *  Temporary for MVP only
     */
    private RecordingElement[] recordingDataset = new RecordingElement[3];

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
         * Temporary for MVP only
         * insert data
         */
        recordingDataset [0] = new RecordingElement("I am date0", "I am duration0" );
        recordingDataset [1] = new RecordingElement("I am date1", "I am duration1" );
        recordingDataset [2] = new RecordingElement("I am date2", "I am duration2" );

        /**
         * Adapter
         */
        mAdapter = new RecordingElementAdapter(recordingDataset);
        mRecyclerView.setAdapter(mAdapter);

        /**
         * fab action
         */
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecordingActivity.class);
                startActivity(intent);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
