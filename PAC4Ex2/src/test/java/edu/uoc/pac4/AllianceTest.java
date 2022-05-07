package edu.uoc.pac4;

import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AllianceTest {

    HumanWarrior characterAlliance;

    @BeforeEach
    void init() {
        try {
            Field field = Alliance.class.getDeclaredField("count");
            field.setAccessible(true);
            field.set(null, 0);
            characterAlliance = new HumanWarrior("Tyrande", 27, Gender.FEMALE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testAlliance(){
        assertEquals("Tyrande", characterAlliance.getName());
        assertEquals(27, characterAlliance.getLevel());
        assertEquals(Gender.FEMALE, characterAlliance.getGender());
        assertEquals(540, characterAlliance.getStamina());
        assertEquals(5400, characterAlliance.getHealth());
    }

    @Test
    void testCount(){
        assertEquals(1, Alliance.getCount());
        try{
            new HumanWarrior("Randa", 12, Gender.FEMALE);
            assertEquals(2, Alliance.getCount());

            new HumanWarrior("Aquaman", 50, Gender.MALE);
            assertEquals(3, Alliance.getCount());

            new GnomeMage("Magui", 50, Gender.FEMALE);
            assertEquals(4, Alliance.getCount());

            new HumanMage("HM", 60, Gender.MALE);
            assertEquals(5, Alliance.getCount());

        }catch(CharacterException e) {
            fail("TestCount failed");
        }
    }

    @Test
    void testWarsong() {
        assertEquals("For the Alliance!!", characterAlliance.warsong());
    }

    @Test
    void testMainCity() {
        assertEquals("Stormwind", characterAlliance.getMainCity());
    }

    @Test
    void testEat() {
        try {
            Food foodVegetable12 = new Food("carrot", 12, FoodType.VEGETABLE);

            assertEquals(5400, characterAlliance.getHealth());
            characterAlliance.decHealth(4000);
            assertEquals(1400, characterAlliance.getHealth());
            characterAlliance.eat(foodVegetable12);
            assertEquals(3800, characterAlliance.getHealth());
            characterAlliance.eat(foodVegetable12);
            assertEquals(5400, characterAlliance.getHealth());
        } catch (CharacterException e) {
            fail("testEat failed");
        }
    }

    @Test
    void testEatException() {

        Food foodMeat55 = new Food("lamb", 55, FoodType.MEAT);
        Food foodVegetable12 = new Food("carrot", 12, FoodType.VEGETABLE);

        CharacterException ex = assertThrows(CharacterException.class, () -> characterAlliance.eat(foodMeat55));
        assertEquals("[ERROR] This character cannot eat this food", ex.getMessage());

        characterAlliance.decHealth(5400);
        ex = assertThrows(CharacterException.class, () -> characterAlliance.eat(foodVegetable12));
        assertEquals("[ERROR] This character cannot eat because is dead", ex.getMessage());
    }

    @Test
    void testToString() {
        assertEquals("I'm a human warrior, I'm from the alliance (1). Name: Tyrande - Level: 27 - Stamina: 540 - Health: 5400", characterAlliance.toString());
        try{
            new HumanWarrior("Aquaman", 50, Gender.MALE);
            new GnomeMage("Magui", 50, Gender.FEMALE);
            new HumanMage("HM", 60, Gender.MALE);
        }catch(CharacterException e) {
            fail("testToString failed");
        }

        assertEquals("I'm a human warrior, I'm from the alliance (4). Name: Tyrande - Level: 27 - Stamina: 540 - Health: 5400", characterAlliance.toString());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        final Class<Alliance> ownClass = Alliance.class;

        //check attribute fields
        assertEquals(1, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("count").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("count").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);


        //check methods, parameters and return types
        assertEquals(6, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getCount").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredMethod("getCount").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("warsong").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getMainCity").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("eat", Food.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("toString").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }

}
