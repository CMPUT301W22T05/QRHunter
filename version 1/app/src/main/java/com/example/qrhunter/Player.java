package com.example.qrhunter;

public class Player {


    public String username,totalScore;
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
