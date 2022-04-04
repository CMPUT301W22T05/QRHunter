package com.example.qrhunter;

import com.google.firebase.firestore.Exclude;

public class Note implements Comparable<Note>{
    private String documentId;
    private String title;
    private String description;
    private int worth;
    private double lat;
    private double lag;
    String url;

    /**
     * This returns a Url of note
     * @return string
     * Return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * This sets a Url of note
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public Note() {
        //public no-arg constructor needed
    }

    /**
     * This returns a document ID of note
     * @return string
     * Return the document ID
     */
    @Exclude
    public String getDocumentId() {
        return this.documentId;
    }

    /**
     * This sets a document ID of note
     */
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Note(String title, String description, int worth, double lat, double lag,String url) {
        this.title = title;
        this.description = description;
        this.worth = worth;
        this.lat = lat;
        this.lag = lag;
        this.url = url;
    }
    public int getWorth(){
        return this.worth;
    }
    public  double getLat(){
        return lat;
    }
    public  double getLag(){
        return lag;
    }

    /**
     * This returns a title of note
     * @return string
     * Return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * This returns a description of note
     * @return string
     * Return the description
     */
    public String getDescription() {
        return description;
    }


    /**
     * This compares the description of two players
     * @return integer
     * Return the integer
     */
    @Override
    public int compareTo(Note note) {
        return this.description.compareTo(note.getDescription());
    }
}
