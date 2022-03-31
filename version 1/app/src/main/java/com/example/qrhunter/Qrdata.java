package com.example.qrhunter;

public class Qrdata {
    String description,title;
    int worth;
    double lag,lat;
    public Qrdata() {
    }

    public Qrdata(String description, double lag, double lat, String title, int worth) {
        this.description = description;
        this.lag = lag;
        this.lat = lat;
        this.title = title;
        this.worth = worth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWorth() {
        return worth;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }

    public Double getLag() {
        return lag;
    }

    public void setLag(Double lag) {
        this.lag = lag;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
