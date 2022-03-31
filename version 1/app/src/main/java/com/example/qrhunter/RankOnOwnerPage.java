package com.example.qrhunter;

public class RankOnOwnerPage {
    public String name, totalScore;

    public RankOnOwnerPage() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public RankOnOwnerPage(String name, String totalScore) {
        this.name = name;
        this.totalScore = totalScore;
    }


}
