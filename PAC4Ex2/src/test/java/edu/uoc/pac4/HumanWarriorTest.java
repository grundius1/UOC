package edu.uoc.pac4;

import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HumanWarriorTest {

    HumanWarrior humanWarrior;

    @BeforeEach
    void init() {
        try {
            Field field = Alliance.class.getDeclaredField("count");
            field.setAccessible(true);
            field.set(null, 0);
            humanWarrior = new HumanWarrior("humanWarrior", 47, Gender.MALE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testHumanWarrior(){
        assertEquals("humanWarrior", humanWarrior.getName());
        assertEquals(47, humanWarrior.getLevel());
        assertEquals(Gender.MALE, humanWarrior.getGender());
        assertEquals(940, humanWarrior.getStamina());
        assertEquals(9400, humanWarrior.getHealth());
    }

    @Test
    void testGetStrength(){
        assertEquals(188, humanWarrior.getStrength());
    }

    @Test
    void testGetAttackPower(){
        assertEquals(752, humanWarrior.getAttackPower());
    }

    @Test
    void testToString() {
        assertEquals("I'm a human warrior, I'm from the alliance (1). Name: humanWarrior - Level: 47 - Stamina: 940 - Health: 9400", humanWarrior.toString());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        final Class<HumanWarrior> ownClass = HumanWarrior.class;

        //check attribute fields
        assertEquals(1, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("strength").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);


        //check methods, parameters and return types
        assertEquals(5, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getStrength").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setStrength").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setAttackPower").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("attack", Character.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("toString").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }

}
