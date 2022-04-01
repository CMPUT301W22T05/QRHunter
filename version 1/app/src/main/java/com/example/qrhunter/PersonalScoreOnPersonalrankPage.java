package com.example.qrhunter;

public class PersonalScoreOnPersonalrankPage {
    private String QRnumber;
    private String personalscore;

    PersonalScoreOnPersonalrankPage(String QRnumbers, String Personalscore) {
        this.QRnumber = QRnumbers;
        this.personalscore= Personalscore;
    }

    public String getQRnumber() {
        return this.QRnumber;
    }

    public String getPersonalscore() {

        return this.personalscore;
    }

}
