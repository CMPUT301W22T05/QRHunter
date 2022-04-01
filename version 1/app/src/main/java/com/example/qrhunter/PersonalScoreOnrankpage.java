package com.example.qrhunter;

public class PersonalScoreOnrankpage {
    private String QRnumber;
    private String personalmark;

    PersonalScoreOnrankpage(String QRnumber, String personalmark){
        this.QRnumber = QRnumber;
        this.personalmark = personalmark;
    }
    public String getQRnumber(){return this.QRnumber;}
    public String getPersonalmark(){return this.personalmark;}

}
