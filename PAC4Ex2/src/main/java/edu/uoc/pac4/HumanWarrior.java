package edu.uoc.pac4;

import static edu.uoc.pac4.CharacterException.*;

public class HumanWarrior extends Alliance implements Melee{

    /**
     * character's ctrenght
     */
    private int strength;

    /**
     * constructor
     * @param name name
     * @param level level
     * @param gender gender
     * @throws CharacterException
     */
    public HumanWarrior(String name, int level, Gender gender) throws CharacterException {
        super(name, level, gender);
        this.setStrength();
        setAttackPower();
    }

    /**
     * Streng getter
     * @return the strenght value
     */
    public int getStrength() {return this.strength;}

    /**
     * strenght setter
     */
    public void setStrength() {
        this.strength = super.getLevel()*4;
    }

    /**
     * attack power setter overides abstract method in character
     */
    @Override
    public void setAttackPower() {
        super.attackPower = this.strength *4;
    }

    /**
     * attack hability of the character overides attack in attacker interface
     * @param target target to attack
     * @throws CharacterException
     */
    @Override
    public void attack(Character target) throws CharacterException {
        if(Melee.super.canAttack(this, target) == true){
            target.decHealth(super.getAttackPower());
        }
        else{throw new CharacterException(MSG_ERR_NOT_ATTACK);}
    }

    /**
     * add human warrior to characteristics string
     * @return add human warrior to characteristics string
     */
    @Override
    public String toString(){
        return "I'm a human warrior, "+super.toString();
    }
}
