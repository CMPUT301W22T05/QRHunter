package com.example.qrhunter;

public class TotalScoreOnOwnerPage implements Comparable<TotalScoreOnOwnerPage>{
    private String username;
    private String totalscore;

    TotalScoreOnOwnerPage(String username, String totalscore) {
        this.username = username;
        this.totalscore = totalscore;
    }

    /**
     * This returns a username of player
     * @return string
     * Return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * This returns a total score of player
     * @return string
     * Return the total score
     */
    public String getTotalscore() {
        return this.totalscore;
    }

    /**
     * This compares the username of two players
     * @return integer
     * Return the integer
     */
    @Override
    public int compareTo(TotalScoreOnOwnerPage totalScoreOnOwnerPage) {
        return this.username.compareTo(totalScoreOnOwnerPage.getUsername());
    }

}
