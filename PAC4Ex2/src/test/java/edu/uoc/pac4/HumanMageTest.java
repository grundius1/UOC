package edu.uoc.pac4;

import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HumanMageTest {

    HumanMage humanMage1;
    HumanMage humanMage2;

    @BeforeEach
    void init() {
        try {
            Field field = Alliance.class.getDeclaredField("count");
            field.setAccessible(true);
            field.set(null, 0);
            humanMage1 = new HumanMage("humanMage1", 38, Gender.MALE);
            humanMage2 = new HumanMage("humanMage2", 54, Gender.FEMALE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testMage(){
        assertEquals("humanMage1", humanMage1.getName());
        assertEquals(38, humanMage1.getLevel());
        assertEquals(Gender.MALE, humanMage1.getGender());
        assertEquals(760, humanMage1.getStamina());
        assertEquals(7600, humanMage1.getHealth());

        assertEquals("humanMage2", humanMage2.getName());
        assertEquals(54, humanMage2.getLevel());
        assertEquals(Gender.FEMALE, humanMage2.getGender());
        assertEquals(1080, humanMage2.getStamina());
        assertEquals(10800, humanMage2.getHealth());
    }

    @Test
    void testGetMana(){
        assertEquals(190, humanMage1.getMana());
        assertEquals(270, humanMage2.getMana());
    }

    @Test
    void testSpentMana(){
        assertEquals(32, humanMage1.spentMana());
        assertEquals(45, humanMage2.spentMana());
    }

    @Test
    void testToString() {
        assertEquals("I'm a human mage, I'm from the alliance (2). Name: humanMage1 - Level: 38 - Stamina: 760 - Health: 7600", humanMage1.toString());
        assertEquals("I'm a human mage, I'm from the alliance (2). Name: humanMage2 - Level: 54 - Stamina: 1080 - Health: 10800", humanMage2.toString());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        final Class<HumanMage> ownClass = HumanMage.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);


        //check methods, parameters and return types
        assertEquals(2, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("spentMana").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("toString").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }

}
