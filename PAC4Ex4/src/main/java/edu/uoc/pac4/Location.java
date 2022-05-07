package edu.uoc.pac4;

public class Location implements Cloneable{
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    @Override
    public Object clone() throws CloneNotSupportedException{
        return (Location) super.clone();
    }
}
