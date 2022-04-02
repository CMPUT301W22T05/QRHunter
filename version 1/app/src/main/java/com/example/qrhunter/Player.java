package com.example.qrhunter;

public class Player {
      // this class is to store the data of accounts when retrieving data from database in player ranking activity

    public String username;
    public String totalScore;
    private String userId;

    public final static String UserPhoneKey = "";

    public Player(String username, String totalScore) {
        this.username = username;
        this.totalScore = totalScore;
    }

    public String getUsername() {
        return username;
    }

    public String getTotalScore() {
        return totalScore;
    }


    public void setUsername(String username) {
        this.username = username;
    }
    public void setuserId(String userId) {
        this.userId = userId;
    }
}
