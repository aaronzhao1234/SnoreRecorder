package uk.ac.shef.oak.snorerecorder.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recording_table")
public class RecordingEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String date;

    private String duration;

    public RecordingEntity(String date, String duration) {
        this.date = date;
        this.duration = duration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }
}
