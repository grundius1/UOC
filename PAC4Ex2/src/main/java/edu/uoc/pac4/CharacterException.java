package edu.uoc.pac4;

public class CharacterException  extends Exception{

    public static String MSG_ERR_NAME_LENGTH = "[ERROR] Character's name cannot be longer than 15 characters";
    public static String MSG_ERR_NAME_FORMAT = "[ERROR] Character's name does not have the correct format";
    public static String MSG_ERR_LEVEL = "[ERROR] Character's level must be in range [10,60]";
    public static String MSG_ERR_EAT = "[ERROR] This character cannot eat this food";
    public static String MSG_ERR_EAT_DEAD = "[ERROR] This character cannot eat because is dead";
    public static String MSG_ERR_NOT_ATTACK = "[ERROR] Cannot attack!!";
    public static String MSG_ERR_NOT_MANA = "[ERROR] You don't have enough mana";

    public CharacterException(){super();}

    public CharacterException(String msg){
        super(msg);
    }
}
