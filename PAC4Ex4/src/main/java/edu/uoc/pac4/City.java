package edu.uoc.pac4;

public class City implements Cloneable{
    private String name;

    private Location location;
    private Detail detail;

    public City(String name, double latitude, double longitude, String faction, int population){
        setName(name);

        location = new Location(latitude, longitude);
        detail = new Detail(faction, population);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public Detail getDetail() {
        return detail;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        //return new City(this.name, this.location.getLatitude(), this.location.getLongitude(), this.detail.getFaction(),this.detail.getPopulation());
        City nueva =  (City) super.clone();
        nueva.location = (Location) location.clone();
        nueva.detail = (Detail) detail.clone();
        return nueva;
    }
}
