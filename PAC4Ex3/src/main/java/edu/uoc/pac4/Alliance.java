package edu.uoc.pac4;

import static edu.uoc.pac4.CharacterException.*;

public abstract class Alliance extends Character {

    /**
     * Total alliance characters
     */
    private static int count = 0;


    /**
     * Constructor
     * @param name Character's name
     * @param level Characters's level
     * @param gender Character's gender
     * @throws CharacterException If any requirments are not met
     */
    public Alliance(String name, int level, Gender gender) throws CharacterException {
        super(name, level, gender);
        incCount();
    }

    /**
     * getter for the total alliance characters
     * @return total alliance characters
     */
    public static int getCount(){return count;}

    /**
     * increaser for the counter
     */
    public static void incCount(){count = count + 1;}


    /**
     * alliance warsong overide Character's warsong
     * @return alliance warsong
     */
    @Override
    public final String warsong(){return "For the Alliance!!";}

    /**
     * Characters main city getter overides Character get main city
     * @return main city
     */
    @Override
    public String getMainCity(){return "Stormwind";}

    /**
     * eat method, overides Character's eat
     * @param food food to eat
     * @throws CharacterException
     */
    @Override
    public void eat(Food food) throws CharacterException{
        if (this.isDead()){
            throw new CharacterException(MSG_ERR_EAT_DEAD);
        }
        else if (this.getLevel() < food.getLevel()){
            throw new CharacterException(MSG_ERR_EAT);
        }
        else{
            this.incHealth(food.getEnergy());
        }
    }

    /**
     * Character's and alliance characteristics in string
     * overides toString in Character
     */
    @Override
    public String toString(){
        return "I'm from the alliance ("+ count+"). " + super.toString();
    }
}
