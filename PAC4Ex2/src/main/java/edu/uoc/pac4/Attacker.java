package edu.uoc.pac4;

public interface Attacker {

    /**
     * abstract method attack, allows a character attacks other
     * @param target target to attack
     * @throws Exception if cannot attack
     */
    public abstract void attack(Character target) throws Exception;

    /**
     * Return true if character can attack, false otherwise
     * @param attacker The attacker
     * @param target the target
     * @return true if character can attack, false otherwise
     */
    default boolean canAttack(Character attacker, Character target){
        if (target.isDead() == false && attacker.isDead() == false){
            if ((attacker instanceof Alliance && target instanceof Horde) || (attacker instanceof Horde && target instanceof Alliance)){
                if (target.getClass() != attacker.getClass()){return true;}
                else{return false;}
            }
            else{return false;}
        }
        else{return false;}
    }
}
