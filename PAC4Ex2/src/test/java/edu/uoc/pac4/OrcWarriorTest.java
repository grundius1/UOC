package edu.uoc.pac4;

import org.junit.jupiter.api.*;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrcWarriorTest {

    OrcWarrior orcWarrior;

    @BeforeEach
    void init() {
        try {
            orcWarrior = new OrcWarrior("orcWarrior", 47, Gender.MALE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testHumanWarrior(){
        assertEquals("orcWarrior", orcWarrior.getName());
        assertEquals(47, orcWarrior.getLevel());
        assertEquals(Gender.MALE, orcWarrior.getGender());
        assertEquals(940, orcWarrior.getStamina());
        assertEquals(9400, orcWarrior.getHealth());
    }

    @Test
    void testGetStrength(){
        assertEquals(235, orcWarrior.getStrength());
    }

    @Test
    void testGetAttackPower(){
        assertEquals(1410, orcWarrior.getAttackPower());
    }

    @Test
    void testToString() {
        assertEquals("I'm a orc warrior, I'm from the horde. Name: orcWarrior - Level: 47 - Stamina: 940 - Health: 9400", orcWarrior.toString());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        final Class<OrcWarrior> ownClass = OrcWarrior.class;

        //check attribute fields
        assertEquals(1, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("strength").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

        //check constructors
        assertEquals(2, ownClass.getDeclaredConstructors().length);


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
