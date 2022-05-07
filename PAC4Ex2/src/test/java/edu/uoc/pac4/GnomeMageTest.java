package edu.uoc.pac4;

import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GnomeMageTest {

    GnomeMage gnomeMage1;
    GnomeMage gnomeMage2;

    @BeforeEach
    void init() {
        try {
            Field field = Alliance.class.getDeclaredField("count");
            field.setAccessible(true);
            field.set(null, 0);
            gnomeMage1 = new GnomeMage("gnomeMage1", 38, Gender.MALE);
            gnomeMage2 = new GnomeMage("gnomeMage2", 54, Gender.FEMALE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testMage(){
        assertEquals("gnomeMage1", gnomeMage1.getName());
        assertEquals(38, gnomeMage1.getLevel());
        assertEquals(Gender.MALE, gnomeMage1.getGender());
        assertEquals(760, gnomeMage1.getStamina());
        assertEquals(7600, gnomeMage1.getHealth());

        assertEquals("gnomeMage2", gnomeMage2.getName());
        assertEquals(54, gnomeMage2.getLevel());
        assertEquals(Gender.FEMALE, gnomeMage2.getGender());
        assertEquals(1080, gnomeMage2.getStamina());
        assertEquals(10800, gnomeMage2.getHealth());
    }

    @Test
    void testGetMana(){
        assertEquals(190, gnomeMage1.getMana());
        assertEquals(270, gnomeMage2.getMana());
    }

    @Test
    void testSpentMana(){
        assertEquals(28, gnomeMage1.spentMana());
        assertEquals(40, gnomeMage2.spentMana());
    }

    @Test
    void testToString() {
        assertEquals("I'm a gnome mage, I'm from the alliance (2). Name: gnomeMage1 - Level: 38 - Stamina: 760 - Health: 7600", gnomeMage1.toString());
        assertEquals("I'm a gnome mage, I'm from the alliance (2). Name: gnomeMage2 - Level: 54 - Stamina: 1080 - Health: 10800", gnomeMage2.toString());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        final Class<GnomeMage> ownClass = GnomeMage.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);


        //check methods, parameters and return types
        assertEquals(3, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getMainCity").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("spentMana").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("toString").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
}
