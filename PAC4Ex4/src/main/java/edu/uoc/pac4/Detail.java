package edu.uoc.pac4;

public class Detail implements Cloneable{

    private String faction;
    private int population;

    public Detail(String faction, int population) {
        setFaction(faction);
        setPopulation(population);
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return (Detail) super.clone();
    }
}
