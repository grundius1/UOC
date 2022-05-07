package edu.uoc.pac4;

import org.apache.commons.lang3.RandomStringUtils;

import static edu.uoc.pac4.CharacterException.*;

public abstract class Horde extends Character{

    /**
     * variable of horde
     * true if the character is npc
     */
    private boolean npc;

    /**
     * default constructor always npc
     * @throws CharacterException if requirements are not met
     */
    public Horde() throws CharacterException {
        super(RandomStringUtils.randomAlphanumeric(6),60,Gender.MALE);
        setNpc(true);

    }

    /**
     * horde constructor
     * @param name character name
     * @param level character level
     * @param gender character gender
     * @throws CharacterException ir requirements are not met
     */
    public Horde(String name, int level, Gender gender) throws CharacterException {
        super(name, level, gender);
        setNpc(false);
    }

    /**
     * npc checker
     * @return true if npc, false otherwise
     */
    public boolean isNpc(){return this.npc;}

    /**
     * setter for npc
     * @param npc true or false
     */
    private void setNpc(boolean npc){this.npc = npc;}

    /**
     * horde warsong method overides warsong in charaacter
     * @return horde warsong
     */
    @Override
    public String warsong(){return "For the Horde!!";}

    /**
     * warsong overloader
     * @param n number of repetitions
     * @return string with all the repetitions
     */
    public String warsong(int n){
        if (n < 1){return "";}
        else{
            return "For the Horde!!".repeat(n);
        }
    }

    /**
     * getter for the main city overides get main city in character
     * @return the main city of the horde
     */
    @Override
    public String getMainCity(){return "Orgrimar";}

    /**
     * allows character to eat, overides eat in character
     * @param food food to eat
     * @throws CharacterException
     */
    @Override
    public void eat(Food food) throws CharacterException{
        if(super.isDead() ){
            throw new CharacterException(MSG_ERR_EAT_DEAD);
        }
        else if (super.getLevel() < food.getLevel() || food.getType() != FoodType.MEAT){
            throw new CharacterException(MSG_ERR_EAT);
        }
        else{
            if (food.getType() != FoodType.MEAT){
                throw new CharacterException(MSG_ERR_EAT);
            }
            else{super.incHealth(food.getEnergy());
            }
        }
    }

    /**
     * string to add horde to the definition
     * @return string to add horde to the definition
     */
    @Override
    public String toString(){
        return "I'm from the horde. " + super.toString();
    }
}
