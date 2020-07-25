package uk.ac.shef.oak.snorerecorder.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RecordingDao {

    @Insert
    void insert (RecordingEntity recordingEntity);

    @Update
    void update (RecordingEntity recordingEntity);

    @Delete
    void delete (RecordingEntity recordingEntity);

    @Query("DELETE FROM RecordingEntity")
    void deleteAllRecordings();

    @Query("SELECT * FROM recordingentity ORDER BY id DESC")
    LiveData<List<RecordingEntity>> getAllRecordings();

    @Query("SELECT * FROM recordingentity WHERE id = (:recordingId)")
    LiveData<List<RecordingEntity>> getRecordingById (int recordingId);
}
