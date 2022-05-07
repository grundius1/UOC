package edu.uoc.pac4;

public enum Gender {

    /**
     * gender posible values
     */
    FEMALE("FEMALE"),
    MALE("MALE");


    /**
     * gender variable
     */
    private String gender;

    /**
     * enum gender constructor
     * @param gender gender to assign
     */
    Gender(String gender){this.gender = gender;}

}
