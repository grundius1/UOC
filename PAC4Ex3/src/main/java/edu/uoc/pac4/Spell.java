package edu.uoc.pac4;

public interface Spell extends Attacker{

    public static final double PERCENTAGE_MANA_SPENT_GNOME = 15.0;
    public static final double PERCENTAGE_MANA_SPENT_HUMAN = 17.0;

    public abstract int getMana();
    public abstract void setMana(int mana);
    public abstract int calculateMaxMana();
    public abstract void setMana();
    public abstract int spentMana();

    default void incMana(int mana) {
        setMana(getMana() + mana);
        if (getMana() > calculateMaxMana()){setMana();}
    }

    default void decMana(int mana) {
        setMana(getMana() - mana);
        if (getMana() < 0){setMana(0);}
    }
}
