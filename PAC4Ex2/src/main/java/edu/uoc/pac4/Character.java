package edu.uoc.pac4;

import java.util.UUID;

import static edu.uoc.pac4.CharacterException.*;

public abstract class Character implements Comparable<Character> {

    /**
     * Character variables
     * ID of th echaracter
     * Character's name
     * Character's level
     * Chracter's Stamina
     * Chracter's health
     * Character's gender
     * Character's attack power
     */
    private UUID id;
    private String name;
    private int level;
    private int stamina;
    private int health;
    protected int attackPower;
    private Gender gender;

    /**
     * Character's constructor
     * @param name Character's name string
     * @param level Character's level int
     * @param gender Character's Gender
     * @throws CharacterException
     */
    public Character(String name, int level, Gender gender) throws CharacterException{
        setId();
        setName(name);
        setLevel(level);
        setGender(gender);
    }

    /**
     * getter for the ID
     * @return the Chraracter's ID
     */
    public UUID getId(){return this.id;}

    /**
     * Setter for the ID
     */
    private void setId(){this.id = UUID.randomUUID();}

    /**
     * Getter for the name
     * @return Character's name
     */
    public String getName() {return name;}

    /**
     * Setter for the name
     * @param name Character's name
     * @throws CharacterException
     */
    public void setName(String name) throws CharacterException{
        if (name.length() > 15){
            throw new CharacterException(MSG_ERR_NAME_LENGTH);
        }
        else if (name.contains(" ") || name.contains(".") || name.contains("-")) {
            throw new CharacterException(MSG_ERR_NAME_FORMAT);
        }
        else{this.name = name;}
    }

    /**
     * Getter for the Character's level
     * @return Character's level
     */
    public int getLevel(){return level;}

    /**
     * Setter for the Level
     * @param level Character's level
     * @throws CharacterException
     */
    public void setLevel(int level) throws CharacterException{
        if ( level < 10 || level > 60 ){
            throw new CharacterException(MSG_ERR_LEVEL);
        }
        else{
            this.level = level;
            this.setStamina();
            this.setHealth();
        }

    }

    /**
     * Getter for the stamina
     * @return the Stamina
     */
    public int getStamina(){return stamina;}

    /**
     * Setter for the Stamina with the characters level
     */
    private void setStamina(){this.stamina = this.getLevel()*20;}

    /**
     * Calculate the max health of the character
     * @return the max possible health of the Character
     */
    private int calculateMaxHealth(){return this.getStamina()*10;}

    /**
     * Getter for the health
     * @return the Character's actual health
     */
    public int getHealth(){return this.health;}

    /**
     * Setter for the Character's health
     */
    private void setHealth(){this.health = calculateMaxHealth();}

    /**
     * Characters Health increaser
     * @param health Th health to increase
     */
    protected void incHealth(int health){
        if (this.health + health > this.calculateMaxHealth()){
            this.setHealth();
        }
        else{
            this.health = this.health + health;
        }
    }

    /**
     * Characters Health decreaser
     * @param health the health to decrease
     */
    protected void decHealth(int health){
        this.health = this.health - health;
        if (this.health < 0){
            this.health = 0;
        }
    }

    /**
     * Characters ded status
     * @return true if dead false if alive
     */
    public boolean isDead(){
        if (this.health == 0) return true;
        else return false;
    }

    /**
     * Resurrect of the character with a 15% of his total health
     */
    public void resurrect(){
        if (this.isDead() == true) this.health = (int) (this.calculateMaxHealth() * 0.15);
    }

    /**
     * Getter for the Character's Gender
     * @return the CHaracter's Gender
     */
    public Gender getGender(){return this.gender;}

    /**
     * Setter for the Gender
     * @param gender The gender of the character
     */
    public void setGender(Gender gender){this.gender = gender;}


    /**
     * Characters comparator
     * @param obj Character tocompare with
     * @return true if the Character is the same, false otherwise
     */
    @Override
    public boolean equals( Object obj){
        if (obj.getClass() == this.getClass()) {
            if (((Character) obj).name == this.name && ((Character) obj).level ==this.level && ((Character) obj).stamina == this.stamina && ((Character) obj).health == this.health && ((Character) obj).gender == this.gender){
                return true;
            }
            else return false;
        }
        else return false;

    }

    /**
     * Characters properties as String
     * @return Characters properties as String
     */
    @Override
    public String toString(){
        return "Name: "+this.name+" - Level: "+this.level+" - Stamina: "+this.stamina+" - Health: "+this.health;
    }

    /**
     * Characters comparator
     * @param character charactero  to compare with
     * @return -1 if the argument Character level is bigger, 1 is its lower and compare names if it's the same
     */
    @Override
    public int compareTo(Character character) {
        if (character == null){throw new NullPointerException();}
        if(this.level > character.level){return 1;}
        else if(this.level < character.level){return -1;}
        else{
            return this.getName().compareTo(character.getName());
            }
        }

    /**
     * Getter for the attack power
     * @return The Character's attack power
     */
    public int getAttackPower(){return this.attackPower;}

    /**
     * Abstract methot warsogn
     * @return nothing here
     */
    public abstract String warsong();

    /**
     * abstract method get main city
     * @return nothing here
     */
    public abstract String getMainCity();

    /**
     * abstract method eat
     * @param food food to eat
     * @throws Exception if Character cannot eat the Food
     */
    public abstract void eat(Food food) throws Exception;

    /**
     * Setter for the Character's attack power, abstarct method
     */
    abstract void setAttackPower();
}
