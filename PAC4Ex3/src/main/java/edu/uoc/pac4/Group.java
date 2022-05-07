package edu.uoc.pac4;


import org.apache.commons.lang3.ObjectUtils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Group {

    /**
     * variables of the group
     * group's name
     * group size
     * tree structure
     */
    private String name;
    private int size;
    private final TreeSet<Character> set = new TreeSet<>();

    /**
     * constructor
     * @param name name
     * @param size size
     */
    public Group(String name, int size){
        setName(name);
        setSize(size);
    }

    /**
     * name getter
     * @return the group's name
     */
    public String getName(){return this.name;}

    /**
     * name setter
     * @param name the groups name
     */
    public void setName(String name){this.name = name;}

    /**
     * group size getter
     * @return the group size
     */
    public int getSize(){return this.size;}

    /**
     * setter for the size
     * @param size the sice (5 or 10)
     */
    public void setSize(int size){
        if (size == 5 || size== 10){this.size = size;}
        else{throw new IllegalArgumentException("[ERROR] Group's size must be 5 or 10");}
    }


    /**
     * list of characters in group getter
     * @return
     */
    public List<Character> getCharacters(){return set.stream().toList();}

    /**
     * add a character in the group
     * @param character character to add
     * @return true if added, false otherwise
     */
    public boolean add (Character character){
        if (character == null){
            throw new NullPointerException("[ERROR] Character object cannot be null");
        }
        else{
            if(this.set.isEmpty()){
                set.add(character);
                return true;
            }
            else{
                //System.out.println(set.stream().toList());
                //System.out.println(character);
                if (set.contains(character)){
                    return false;
                }
                else{
                    if (set.stream().count() == size){
                        return false;
                    }
                    else if (set.first() instanceof Alliance && character instanceof Alliance){
                        set.add(character);
                        return true;
                    }
                    else if(set.first() instanceof Horde && character instanceof Horde){
                        set.add(character);
                        return true;
                    }
                    else{return false;}
                }
            }
        }
    }

    /**
     * character remover
     * @param character character to remove
     * @return true if remover, false otherwise
     */
    public boolean remove(Character character){
        if (character == null){
            throw new NullPointerException("[ERROR] Character object cannot be null");
        }
        if (set.contains(character)){
            this.set.remove(character);
            return true;
        }
        else{return false;}

    }

    /**
     * clear all the group
     * @return true if removed
     */
    public boolean remove(){
        set.clear();
        return true;
    }

    /**
     * return true if a characters exist in group
     * @param character character to search
     * @return true if exists, false otherwise
     */
    public boolean exists(Character character){
        if (set.contains(character)){return true;}
        else{return false;}
    }

    /**
     * check ig group is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty(){return set.isEmpty();}

    /**
     * group characteristics string
     * @return
     */
    public String toString(){return "Group: " + this.name +" - Size: "+ this.size + " - Characters: " + set.stream().count();}

    /**
     * gives a list with the names of mages with more or equal mana than param
     * @param mana mana to check
     * @return list of characters
     */
    public List<String> getMagesWithManaGreaterThanAndOrderByName(int mana) {
        return this.set.stream().filter(character -> character instanceof Mage && ((Mage) character).getMana() >= mana)
                .sorted(Comparator.comparing(Character::getName)).map(Character::getName).collect(Collectors.toList());
    }

    /**
     * resurrect all dead characters
     */
    public void resurrectAllTheDead(){
        this.set.stream().distinct().filter(Character::isDead).sorted(Comparator.comparing(Character::getName))
                .forEach(character -> {character.resurrect();System.out.println(character.getName());});
    }

    /**
     * return the group average level
     * @return the average group level
     */
    public double averageLevelGroup() {
        return this.set.stream().mapToDouble(Character::getLevel).average().getAsDouble();
    }
}
