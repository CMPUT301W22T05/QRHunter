package com.example.qrhunter;

public class QrCodes implements Comparable<QrCodes>{
    String description,title;
    int worth;
    double lag,lat;
    String url;

    /**
     * This returns a Url of note
     * @return string
     * Return the url
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public QrCodes() {
    }

    public QrCodes(String description, double lag, double lat, String title, int worth,String url) {
        this.description = description;
        this.lag = lag;
        this.lat = lat;
        this.title = title;
        this.worth = worth;
        this.url = url;
    }

    /**
     * This returns a description of note
     * @return string
     * Return the description
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This returns a title of note
     * @return string
     * Return the title
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This returns a worth of qr code
     * @return integer
     * Return the worth
     */
    public int getWorth() {
        return worth;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }

    /**
     * This returns a longitude of qr code
     * @return string
     * Return the longitude
     */
    public Double getLag() {
        return lag;
    }

    public void setLag(Double lag) {
        this.lag = lag;
    }

    /**
     * This returns a latitude of qr code
     * @return string
     * Return the latitude
     */
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * This compares the description of two players
     * @return integer
     * Return the integer
     */
    @Override
    public int compareTo(QrCodes qrcode) {
        return this.description.compareTo(qrcode.getDescription());
    }
}
