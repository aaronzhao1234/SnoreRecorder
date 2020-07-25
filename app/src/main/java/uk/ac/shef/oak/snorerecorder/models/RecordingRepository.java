package uk.ac.shef.oak.snorerecorder.models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecordingRepository {

    private RecordingDao recordingDao;
    private LiveData<List<RecordingEntity>> allRecording;

    public RecordingRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        recordingDao = appDatabase.recordingDao();
        allRecording = recordingDao.getAllRecordings();
    }

    public void insert(RecordingEntity recordingEntity) {
        new InsertRecordingAsyncTask(recordingDao).execute(recordingEntity);
    }

    public  void update(RecordingEntity recordingEntity) {
        new UpdatingRecordingAsyncTask(recordingDao).execute(recordingEntity);
    }

    public void delete(RecordingEntity recordingEntity) {
        new DeleteRecordingAsyncTask(recordingDao).execute(recordingEntity);
    }

    public void deleteAllRecordings() {
        new DeleteAllRecordingAsyncTask(recordingDao).execute();
    }

    public LiveData<List<RecordingEntity>> getAllRecording() {
        return allRecording;
    }

    public LiveData<List<RecordingEntity>> getRecordingById(int recordingId) {
        return recordingDao.getRecordingById(recordingId);
    }



    /**
     * Async tasks for background
     */
    private static class InsertRecordingAsyncTask extends AsyncTask<RecordingEntity,Void,Void > {
        private RecordingDao recordingDao;
        private InsertRecordingAsyncTask(RecordingDao  recordingDao) {
            this.recordingDao = recordingDao;
        }

        @Override
        protected Void doInBackground(RecordingEntity... recordingEntities) {
            recordingDao.insert(recordingEntities[0]);
            return null;
        }
    }

    private static class UpdatingRecordingAsyncTask extends AsyncTask<RecordingEntity,Void,Void > {
        private RecordingDao recordingDao;

        private UpdatingRecordingAsyncTask(RecordingDao  recordingDao) {
            this.recordingDao = recordingDao;
        }

        @Override
        protected Void doInBackground(RecordingEntity... recordingEntities) {
            recordingDao.update(recordingEntities[0]);
            return null;
        }
    }

    private static class DeleteRecordingAsyncTask extends AsyncTask<RecordingEntity,Void,Void > {
        private RecordingDao recordingDao;

        private DeleteRecordingAsyncTask(RecordingDao  recordingDao) {
            this.recordingDao = recordingDao;
        }

        @Override
        protected Void doInBackground(RecordingEntity... recordingEntities) {
            recordingDao.delete(recordingEntities[0]);
            return null;
        }
    }

    private static class DeleteAllRecordingAsyncTask extends AsyncTask<Void,Void,Void > {
        private RecordingDao recordingDao;

        private DeleteAllRecordingAsyncTask(RecordingDao  recordingDao) {
            this.recordingDao = recordingDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recordingDao.deleteAllRecordings();
            return null;
        }
    }
}
