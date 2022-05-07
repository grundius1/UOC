package edu.uoc.pac4;

public class CharacterException  extends Exception{

    public static String MSG_ERR_NAME_LENGTH = "[ERROR] Character's name cannot be longer than 15 characters";
    public static String MSG_ERR_NAME_FORMAT = "[ERROR] Character's name does not have the correct format";
    public static String MSG_ERR_LEVEL = "[ERROR] Character's level must be in range [10,60]";

    public CharacterException(){super();}

    public CharacterException(String msg){
        super(msg);
    }
}
