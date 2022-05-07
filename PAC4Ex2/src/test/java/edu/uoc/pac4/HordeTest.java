package edu.uoc.pac4;

import org.junit.jupiter.api.*;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HordeTest {

    OrcWarrior characterHorde;
    OrcWarrior characterNpc;

    @BeforeEach
    void init() {
        try {
            characterHorde = new OrcWarrior("Thrall", 45, Gender.MALE);
            characterNpc = new OrcWarrior();
        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testHordeDefault(){
        assertTrue(characterNpc.isNpc());
        assertTrue(characterNpc.getName().length()==6);
        assertEquals(60, characterNpc.getLevel());
        assertTrue(characterNpc.getGender().equals(Gender.MALE) || characterNpc.getGender().equals(Gender.FEMALE));
    }

    @Test
    void testHorde(){
        assertFalse(characterHorde.isNpc());

        assertEquals("Thrall", characterHorde.getName());
        assertEquals(45, characterHorde.getLevel());
        assertEquals(Gender.MALE, characterHorde.getGender());
        assertEquals(900, characterHorde.getStamina());
        assertEquals(9000, characterHorde.getHealth());
    }

    @Test
    void testWarsong() {
        assertEquals("For the Horde!!", characterHorde.warsong());
    }

    @Test
    void testWarsongOverloading() {
        assertEquals("For the Horde!!", characterHorde.warsong(1));
        assertEquals("For the Horde!!For the Horde!!", characterHorde.warsong(2));
        assertEquals("For the Horde!!For the Horde!!For the Horde!!For the Horde!!For the Horde!!", characterHorde.warsong(5));
        assertEquals("", characterHorde.warsong(0));
        assertEquals("", characterHorde.warsong(-1));
    }

    @Test
    void testMainCity() {
        assertEquals("Orgrimar", characterHorde.getMainCity());
    }

    @Test
    void testEat() {
        try {
            Food foodMeat20 = new Food("pork", 20, FoodType.MEAT);

            assertEquals(9000, characterHorde.getHealth());
            characterHorde.decHealth(5500);
            assertEquals(3500, characterHorde.getHealth());
            characterHorde.eat(foodMeat20);
            assertEquals(7500, characterHorde.getHealth());
            characterHorde.eat(foodMeat20);
            assertEquals(9000, characterHorde.getHealth());
        } catch (CharacterException e) {
            fail("testEat failed");
        }
    }

    @Test
    void testEatException() {

        Food foodMeat55 = new Food("lamb", 55, FoodType.MEAT);
        Food foodVegetable12 = new Food("carrot", 12, FoodType.VEGETABLE);

        CharacterException ex = assertThrows(CharacterException.class, () -> characterHorde.eat(foodMeat55));
        assertEquals("[ERROR] This character cannot eat this food", ex.getMessage());

        ex = assertThrows(CharacterException.class, () -> characterHorde.eat(foodVegetable12));
        assertEquals("[ERROR] This character cannot eat this food", ex.getMessage());

        characterHorde.decHealth(10000);

        ex = assertThrows(CharacterException.class, () -> characterHorde.eat(foodVegetable12));
        assertEquals("[ERROR] This character cannot eat because is dead", ex.getMessage());
    }

    @Test
    void testToString() {
        assertEquals("I'm a orc warrior, I'm from the horde. Name: Thrall - Level: 45 - Stamina: 900 - Health: 9000", characterHorde.toString());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        final Class<Horde> ownClass = Horde.class;

        //check attribute fields
        assertEquals(1, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("npc").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

        //check constructors
        assertEquals(2, ownClass.getDeclaredConstructors().length);


        //check methods, parameters and return types
        assertEquals(7, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("isNpc").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setNpc", boolean.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("warsong").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("warsong", int.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getMainCity").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("eat", Food.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("toString").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }

}
