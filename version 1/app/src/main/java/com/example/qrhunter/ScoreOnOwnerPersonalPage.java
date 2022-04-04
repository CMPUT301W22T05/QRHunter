package com.example.qrhunter;

public class ScoreOnOwnerPersonalPage implements Comparable<ScoreOnOwnerPersonalPage>{
    private String SequenceNumber;
    private String Score;
    private String id;

    ScoreOnOwnerPersonalPage(String username, String totalscore, String id) {
        this.SequenceNumber = username;
        this.Score = totalscore;
        this.id = id;
    }

    /**
     * This returns an id of class
     * @return string
     * Return the id
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    /**
     * This returns a sequence number of class
     * @return string
     * Return the sequence number
     */
    public String getSequenceNumber() {
        return this.SequenceNumber;
    }

    /**
     * This returns a score of class
     * @return string
     * Return the score
     */
    public String getScore() {
        return this.Score;
    }

    /**
     * This compares the sequence number of two classes
     * @return integer
     * Return the integer
     */
    @Override
    public int compareTo(ScoreOnOwnerPersonalPage scoreOnOwnerPersonalPage) {
        return this.SequenceNumber.compareTo(scoreOnOwnerPersonalPage.getSequenceNumber());
    }
}