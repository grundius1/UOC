package edu.uoc.pac4;

public class HumanMage extends Mage{

    /**
     * human mage contructor
     * @param name character name
     * @param level character level
     * @param gender character gender
     * @throws CharacterException
     */
    public HumanMage(String name, int level, Gender gender) throws CharacterException   {
        super(name,level,gender);
    }

    /**
     * ma spent returner, overload spent mana in spell
     * @return the mana to spend
     */
    @Override
    public int spentMana() {
        return (int) (super.calculateMaxMana()*Spell.PERCENTAGE_MANA_SPENT_HUMAN/100);
    }

    /**
     * homan mage caracteristic adder
     * @return human mage characteristic added to string
     */
    public String toString(){
        return "I'm a human mage, "+super.toString();
    }
}
