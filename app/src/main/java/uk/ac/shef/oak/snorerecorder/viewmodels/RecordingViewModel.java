package uk.ac.shef.oak.snorerecorder.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import uk.ac.shef.oak.snorerecorder.models.RecordingEntity;
import uk.ac.shef.oak.snorerecorder.models.RecordingRepository;

public class RecordingViewModel extends AndroidViewModel {
    private RecordingRepository recordingRepository;
    private LiveData<List<RecordingEntity>> allRecordings;

    public RecordingViewModel(@NonNull Application application) {
        super(application);
        recordingRepository = new RecordingRepository(application);
        allRecordings = recordingRepository.getAllRecording();
    }

    public void insert(RecordingEntity recordingEntity){
        recordingRepository.insert(recordingEntity);
    }

    public void update(RecordingEntity recordingEntity) {
        recordingRepository.update(recordingEntity);
    }

    public void delete(RecordingEntity recordingEntity) {
        recordingRepository.delete(recordingEntity);
    }

    public void deleteAllNotes(){
        recordingRepository.deleteAllRecordings();
    }

    public LiveData<List<RecordingEntity>> getAllRecordings() {
        return allRecordings;
    }

    public LiveData<List<RecordingEntity>> getRecordingById(int recordingId) {
        return recordingRepository.getRecordingById(recordingId);
    }
}
