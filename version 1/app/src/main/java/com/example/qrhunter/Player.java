package com.example.qrhunter;

import java.util.Collection;

public class Player implements Comparable<Player>{
      // this class is to store the data of accounts when retrieving data from database in player ranking activity

    public String username;
    public String totalScore;
    private String userId;

    public final static String UserPhoneKey = "";

    Player(String username, String totalScore) {
        this.username = username;
        this.totalScore = totalScore;
    }

    /**
     * This returns a username of player
     * @return string
     * Return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * This returns a total score of player
     * @return string
     * Return the total score
     */
    public String getTotalScore() {
        return totalScore;
    }

    /**
     * This sets the username of player
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This sets the user id of player
     */
    public void setuserId(String userId) {
        this.userId = userId;
    }


    /**
     * This compares the username of two players
     * @return integer
     * Return the integer
     */
    @Override
    public int compareTo(Player player) {
        return this.username.compareTo(player.getUsername());
    }

}

