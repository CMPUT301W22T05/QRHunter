package com.example.qrhunter;

public class ScoreOnOwnerPersonalPage {
    private String SequenceNumber;
    private String Score;
    private String id;

    ScoreOnOwnerPersonalPage(String username, String totalscore, String id) {
        this.SequenceNumber = username;
        this.Score = totalscore;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSequenceNumber() {
        return this.SequenceNumber;
    }

    public String getScore() {
        return this.Score;
    }
}