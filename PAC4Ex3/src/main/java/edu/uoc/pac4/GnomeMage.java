package edu.uoc.pac4;

public class GnomeMage extends Mage{

    /**
     * Gnome mage variables
     * @param name character name
     * @param level character level
     * @param gender character gender
     * @throws CharacterException if requirments ar not met
     */
    public GnomeMage(String name, int level, Gender gender) throws CharacterException {
        super(name, level, gender);
    }

    /**
     * Getter for main city overides get main city in alliance
     * @return the gnomes main city
     */
    @Override
    public String getMainCity(){
        return "Gnomeran";
    }

    /**
     * mana spent calculation for mages overides spent mana in Spell interface
     * @return the amount of mana to use
     */
    @Override
    public int spentMana() {
        return (int) (super.calculateMaxMana()*Spell.PERCENTAGE_MANA_SPENT_GNOME/100);
    }

    /**
     * String to add the gnome mage to characteristics
     * @return String to add the gnome mage to characteristics
     */
    public String toString(){
        return "I'm a gnome mage, "+super.toString();
    }
}
