package uk.ac.shef.oak.snorerecorder.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TimeFormatException;
import android.view.View;
import android.widget.Button;

import java.text.DateFormat;
import java.util.Calendar;

import uk.ac.shef.oak.snorerecorder.R;

public class RecordingActivity extends AppCompatActivity {

    public static final String EXTRA_DATE =
            "package uk.ac.shef.oak.snorerecorder.views.EXTRA_DATE";
    public static final String EXTRA_START_TIME =
            "package uk.ac.shef.oak.snorerecorder.views.EXTRA_START_TIME";
    public static final String EXTRA_STOP_TIME =
            "package uk.ac.shef.oak.snorerecorder.views.EXTRA_STOP_TIME";

    private Button mStartButton;
    private Button mStopButton;
    private String mDate;
    private String mStartTime;
    private String mStopTime;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        mStartButton = findViewById(R.id.start_button);
        mStopButton = findViewById(R.id.stop_button);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStartButton.setEnabled(false);
                mStopButton.setEnabled(true);
                /**
                 * TODO: add recording code
                 */
                calendar = Calendar.getInstance();
                mDate = DateFormat.getDateInstance().format(calendar.getTime());
                mStartTime = DateFormat.getTimeInstance().format(calendar.getTime());
            }
        });

        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: add save recording code
                 */
                calendar = Calendar.getInstance();
                mStopTime = DateFormat.getTimeInstance().format(calendar.getTime());
                saveRecording();
            }

            /**
             * Pass the data to the MainActivity
             */
            private void saveRecording() {
                Intent dataIntent = new Intent();
                dataIntent.putExtra(EXTRA_DATE, mDate);
                dataIntent.putExtra(EXTRA_START_TIME, mStartTime);
                dataIntent.putExtra(EXTRA_STOP_TIME, mStopTime);
                setResult(RESULT_OK, dataIntent);
                finish();
            }
        });
    }
}
