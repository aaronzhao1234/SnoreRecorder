package uk.ac.shef.oak.snorerecorder.models;

/**
 *  this class represents the data supporting the recycler adapter
 */
public class RecordingElement {
    private String date;
    private String duration;

    public RecordingElement(String date, String duration) {
        this.date = date;
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }
}
