package edu.uoc.pac4;

public class Food {

    /**
     * Food variables
     * Food's name
     * Food's level
     * Food's energy
     * Food's Type
     */
    private String name;
    private int level;
    private int energy;
    private FoodType type;


    /**
     * Default constructor for Food
     */
    public Food(){
        this("water", 10 , FoodType.valueOf("WATER"));
    }

    /**
     * Contructor for Food
     * @param name Food name
     * @param level Food level
     * @param foodtype Food type
     * @throws IllegalArgumentException if lever is not correct
     */
    public Food(String name, int level, FoodType foodtype) throws IllegalArgumentException {
        if (level < 10 || level > 60){
            throw new IllegalArgumentException("[ERROR] Food's level must be in range [10,60]");
        }
        else{
            this.name = name.toUpperCase().trim().replace(" ","_");
            this.level = level;
            this.energy = level*200;
            this.type = foodtype;
        }
    }

    /**
     * Getter for foods name
     * @return the food name
     */
    public String getName(){return this.name;}

    /**
     * Getter for food'slevel
     * @return Food's level
     */
    public int getLevel(){return this.level;}

    /**
     * getter for the energy
     * @returnthe Food's energy
     */
    public int getEnergy(){return this.energy;}

    /**
     * getter for the type
     * @return Food type
     */
    public FoodType getType(){return this.type;}

    /**
     * String of the foods properties
     * @return String of the foods properties
     */
    @Override
    public String toString(){return "("+this.type+") "+this.name+"="+this.energy; }
}
