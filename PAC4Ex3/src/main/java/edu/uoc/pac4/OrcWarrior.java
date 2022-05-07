package edu.uoc.pac4;

import static edu.uoc.pac4.CharacterException.*;

public class OrcWarrior extends Horde implements Melee{

    /**
     * strenght characterictic
     */
    private int strength;

    /**
     * default contructor
     * @throws CharacterException
     */
    public OrcWarrior() throws CharacterException {
        super();
        this.setStrength();
        setAttackPower();
    }

    /**
     * condtructor
     * @param name name
     * @param level level
     * @param gender gender
     * @throws CharacterException
     */
    public OrcWarrior(String name, int level, Gender gender) throws CharacterException {
        super(name, level, gender);
        this.setStrength();
        setAttackPower();;
    }

    /**
     * strenght getter
     * @return the strenght
     */
    @Override
    public int getStrength() {return this.strength;}

    /**
     * strenght setter
     */
    @Override
    public void setStrength() {
        this.strength = super.getLevel()*5;
    }

    /**
     * attack power setter
     */
    @Override
    public void setAttackPower() {
        super.attackPower = this.getStrength()*6;
    }

    /**
     * character habilituy to attack
     * @param target target to attack
     * @throws CharacterException
     */
    @Override
    public void attack(Character target) throws CharacterException {
        if (Melee.super.canAttack(this, target)){
            if (super.isNpc() == true){
                target.decHealth(super.getAttackPower()*2);
            }
            else{target.decHealth(super.getAttackPower());}
        }
        else{
            throw new CharacterException(MSG_ERR_NOT_ATTACK);
        }
    }

    /**
     * add orc warior to charaacteristics string
     * @return add orc warior to charaacteristics string
     */
    public String toString(){return "I'm a orc warrior, " + super.toString();}
}
