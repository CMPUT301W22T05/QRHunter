package com.example.qrhunter;

public class TotalScoreOnOwnerPage {
    private String username;
    private String totalscore;

    TotalScoreOnOwnerPage(String username, String totalscore) {
        this.username = username;
        this.totalscore = totalscore;
    }

    public String getUsername() {
        return this.username;
    }

    public String getTotalscore() {

        return this.totalscore;
    }

}
