package com.example.qrhunter;

import com.google.firebase.firestore.Exclude;

public class Note {
    private String documentId;
    private String title;
    private String description;
    private int worth;
    private double lat;
    private double lag;

    public Note() {
        //public no-arg constructor needed
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Note(String title, String description, int worth, double lat, double lag) {
        this.title = title;
        this.description = description;
        this.worth = worth;
        this.lat = lat;
        this.lag = lag;
    }
    public  int getWorth(){
        return worth;
    }
    public  double getLat(){
        return lat;
    }
    public  double getLag(){
        return lag;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
