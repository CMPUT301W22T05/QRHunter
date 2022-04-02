package com.example.qrhunter;

public class ScoreOnOwnerPersonalPage {
    private String SequenceNumber;
    private String Score;

    ScoreOnOwnerPersonalPage(String username, String totalscore) {
        this.SequenceNumber = username;
        this.Score = totalscore;
    }

    public String getSequenceNumber() {
        return this.SequenceNumber;
    }

    public String getScore() {
        return this.Score;
    }
}
