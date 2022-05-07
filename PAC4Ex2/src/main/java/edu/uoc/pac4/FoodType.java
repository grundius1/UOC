package edu.uoc.pac4;

public enum FoodType {

    /**
     * allowable food types
     */
    MEAT("MEAT"),
    FISH("FISH"),
    VEGETABLE("VEGETABLE"),
    WATER("WATER");

    /**
     * Food variable
     */
    private String foodtype;

    /**
     * Constructor
     * @param foodtype food type
     */
    FoodType(String foodtype){this.foodtype = foodtype;}
}
