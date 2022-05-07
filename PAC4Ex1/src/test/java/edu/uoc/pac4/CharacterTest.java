package edu.uoc.pac4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class CharacterTest {

    Character character1;
    Character character2;

    @BeforeEach
    void init() {
        try {
            character1 = new Character("Tyrande", 38, Gender.FEMALE);
            character2 = new Character("Anduin", 60, Gender.MALE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testId() {
        assertEquals(36, character1.getId().toString().length());
        assertEquals(36, character2.getId().toString().length());
        assertNotEquals(character1.getId(), character2.getId());
    }

    @Test
    void testGetName() {
        assertEquals("Tyrande", character1.getName());
        assertEquals("Anduin", character2.getName());
    }

    @Test
    void testSetName() {
        try{
            character1.setName("Frank_length_15");
            assertEquals("Frank_length_15", character1.getName());
        }catch(Exception e) {
            fail("setName failed");
        }

        Exception ex = assertThrows(Exception.class, () ->	character1.setName("Frank_length__16"));
        assertEquals(CharacterException.MSG_ERR_NAME_LENGTH, ex.getMessage());

        ex = assertThrows(Exception.class, () -> character1.setName("juri an"));
        assertEquals(CharacterException.MSG_ERR_NAME_FORMAT, ex.getMessage());

        ex = assertThrows(Exception.class, () -> character1.setName("juri.an"));
        assertEquals(CharacterException.MSG_ERR_NAME_FORMAT, ex.getMessage());

        ex = assertThrows(Exception.class, () -> character1.setName("juri-an"));
        assertEquals(CharacterException.MSG_ERR_NAME_FORMAT, ex.getMessage());

        assertEquals("Frank_length_15", character1.getName());
    }


    @Test
    void testGetLevel() {
        assertEquals(38, character1.getLevel());
        assertEquals(60, character2.getLevel());
    }

    @Test
    void testSetLevel() {
        try{
            character1.setLevel(30);
            assertEquals(30, character1.getLevel());
        }catch(Exception e) {
            fail("testSetLevel failed");
        }

        Exception ex = assertThrows(Exception.class, () ->	character1.setLevel(9));
        assertEquals(CharacterException.MSG_ERR_LEVEL, ex.getMessage());

        ex = assertThrows(Exception.class, () -> character1.setLevel(61));
        assertEquals(CharacterException.MSG_ERR_LEVEL, ex.getMessage());

    }

    @Test
    void testGetStamina() {
        assertEquals(760, character1.getStamina());
        assertEquals(1200, character2.getStamina());
    }

    @Test
    void testSetStamina() {
        try{
            character1.setLevel(30);
            assertEquals(600, character1.getStamina());

            character1.setLevel(31);
            assertEquals(620, character1.getStamina());

            character1.setLevel(32);
            assertEquals(640, character1.getStamina());

            character1.setLevel(55);
            assertEquals(1100, character1.getStamina());

            character1.setLevel(60);
            assertEquals(1200, character1.getStamina());
        }catch(Exception e) {
            fail("testSetStamina failed");
        }
    }

    @Test
    void testGetHealth() {
        assertEquals(7600, character1.getHealth());
        assertEquals(12000, character2.getHealth());
    }

    @Test
    void testSetHealth() {
        try{
            character1.setLevel(30);
            assertEquals(6000, character1.getHealth());

            character1.setLevel(31);
            assertEquals(6200, character1.getHealth());

            character1.setLevel(32);
            assertEquals(6400, character1.getHealth());

            character1.setLevel(55);
            assertEquals(11000, character1.getHealth());

            character1.setLevel(60);
            assertEquals(12000, character1.getHealth());
        }catch(Exception e) {
            fail("testSetHealth failed");
        }
    }

    @Test
    void testIncDecHealth() {
        //dec character 1
        assertEquals(7600, character1.getHealth());
        character1.decHealth(100);
        assertEquals(7500, character1.getHealth());
        character1.decHealth(1500);
        assertEquals(6000, character1.getHealth());
        character1.decHealth(7000);
        assertEquals(0, character1.getHealth());

        //dec character 2
        assertEquals(12000, character2.getHealth());
        character2.decHealth(12000);
        assertEquals(0, character2.getHealth());

        //inc character 1
        character1.incHealth(700);
        assertEquals(700, character1.getHealth());
        character1.incHealth(700);
        assertEquals(1400, character1.getHealth());
        character1.incHealth(8000);
        assertEquals(7600, character1.getHealth());

        //inc character 2
        assertEquals(0, character2.getHealth());
        character2.incHealth(11999);
        assertEquals(11999, character2.getHealth());
        character2.incHealth(2);
        assertEquals(12000, character2.getHealth());
    }



    @Test
    void testIsDead() {
        assertFalse(character1.isDead());
        character1.decHealth(7600);
        assertTrue(character1.isDead());

        assertFalse(character2.isDead());
        character2.decHealth(20000);
        assertTrue(character2.isDead());
    }

    @Test
    void testResurrect() {
        assertFalse(character1.isDead());
        character1.resurrect(); //do nothing because is not dead
        assertFalse(character1.isDead());
        character1.decHealth(7600);
        assertTrue(character1.isDead());
        character1.resurrect(); //resurrect character1
        assertFalse(character1.isDead());
    }

    @Test
    void testGetGender() {
        assertEquals(Gender.FEMALE, character1.getGender());
        assertEquals(Gender.MALE, character2.getGender());
    }

    @Test
    void testSetGender() {
        assertEquals(Gender.FEMALE, character1.getGender());
        character1.setGender(Gender.MALE);
        assertEquals(Gender.MALE, character1.getGender());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        final Class<Character> ownClass = Character.class;

        //check attribute fields
        assertEquals(6, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("id").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("name").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("level").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("stamina").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("health").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("gender").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);


        //check methods, parameters and return types
        assertEquals(19, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getId").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setId").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getName").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setName", String.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getLevel").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setLevel", int.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getStamina").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setStamina").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("calculateMaxHealth").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getHealth").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setHealth").getModifiers()));
            assertTrue(Modifier.isProtected(ownClass.getDeclaredMethod("incHealth", int.class).getModifiers()));
            assertTrue(Modifier.isProtected(ownClass.getDeclaredMethod("decHealth", int.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("isDead").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("resurrect").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getGender").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setGender", Gender.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("equals", Object.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("toString").getModifiers()));

        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }

    }
}