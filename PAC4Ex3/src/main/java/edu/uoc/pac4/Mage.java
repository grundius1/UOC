package edu.uoc.pac4;

import static edu.uoc.pac4.CharacterException.*;

public abstract class Mage  extends Alliance implements Spell{

    /**
     * mana value
     */
    private int mana;

    /**
     * mage contructor
     * @param name name
     * @param level level
     * @param gender gender
     * @throws CharacterException
     */
    public Mage(String name, int level, Gender gender) throws CharacterException {
        super(name, level, gender);
        setMana();
        setAttackPower();
    }

    /**
     * getter for the mana  overides from spell
     * @return the mana
     */
    @Override
    public int getMana(){return this.mana;}

    /**
     * setter for the mana
     * @param mana mana of the character
     */
    @Override
    public void setMana(int mana){this.mana = mana;}

    /**
     * maxmana calculator
     * @return the max possible mana
     */
    @Override
    public int calculateMaxMana(){
        return super.getLevel()*5;
    }

    /**
     * max mana setter
     */
    @Override
    public void setMana(){this.mana = this.calculateMaxMana();}

    /**
     * setter for the attack power
     */
    @Override
    public void setAttackPower(){super.attackPower = this.calculateMaxMana()*4;}

    /**
     * eat character hability
     * @param food food to eat
     * @throws CharacterException
     */
    @Override
    public void eat(Food food) throws CharacterException {
        if (super.isDead()){
            throw new CharacterException(MSG_ERR_EAT_DEAD);
        }
        else if (super.getLevel() < food.getLevel()){
            throw new CharacterException(MSG_ERR_EAT);
        }
        else{
            if (food.getType() == FoodType.WATER){this.incMana(100);}
            super.incHealth(food.getEnergy());
        }
    }

    /**
     * character attack hability
     * @param target target to attack
     * @throws CharacterException
     */
    @Override
    public void attack(Character target) throws CharacterException{
        if (this.getMana() < spentMana()){throw new CharacterException(MSG_ERR_NOT_MANA);}
        if(Spell.super.canAttack(this,target)==true){
            target.decHealth(super.getAttackPower());
            this.decMana(spentMana());
        }else{
            throw new CharacterException(MSG_ERR_NOT_ATTACK);
        }
    }
}
