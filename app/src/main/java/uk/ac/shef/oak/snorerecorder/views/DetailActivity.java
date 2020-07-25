package uk.ac.shef.oak.snorerecorder.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import uk.ac.shef.oak.snorerecorder.R;
import uk.ac.shef.oak.snorerecorder.adapters.RecordingElementAdapter;
import uk.ac.shef.oak.snorerecorder.models.RecordingEntity;
import uk.ac.shef.oak.snorerecorder.viewmodels.RecordingViewModel;

public class DetailActivity extends AppCompatActivity {

    private RecordingViewModel recordingViewModel;
    private RecordingEntity recordingEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int id = getIntent().getExtras().getInt("recordingId",-1);
        recordingViewModel = new RecordingViewModel(getApplication());
        recordingViewModel.getRecordingById(id).observe(this, new Observer<List<RecordingEntity>>() {
            @Override
            public void onChanged(List<RecordingEntity> recordingEntities) {
                recordingEntity = recordingEntities.get(0);
                setText();
            }
        });
    }

    private void setText() {
        TextView durationTextView = findViewById(R.id.detail_duration_textView);
        durationTextView.setText(recordingEntity.getDuration());
        TextView dateTextView = findViewById(R.id.detail_date_textView);
        dateTextView.setText(recordingEntity.getDate());
    }
}
