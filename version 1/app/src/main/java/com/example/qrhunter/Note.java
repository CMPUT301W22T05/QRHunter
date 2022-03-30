package com.example.qrhunter;

import com.google.firebase.firestore.Exclude;

public class Note {
    private String documentId;
    private String title;
    private String description;
    private int worth;

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

    public Note(String title, String description, int worth) {
        this.title = title;
        this.description = description;
        this.worth = worth;
    }
    public  int getWorth(){
        return worth;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
