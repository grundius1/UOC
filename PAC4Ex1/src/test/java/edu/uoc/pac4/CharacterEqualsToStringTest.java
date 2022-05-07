package edu.uoc.pac4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class CharacterEqualsToStringTest {

    Character character1;
    Character character2;

    @BeforeEach
    void init() {
        try {
            character1 = new Character("Tyrande", 27, Gender.FEMALE);
            character2 = new Character("Anduin", 60, Gender.MALE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testToString() {
        assertEquals("Name: Tyrande - Level: 27 - Stamina: 540 - Health: 5400", character1.toString());
        assertEquals("Name: Anduin - Level: 60 - Stamina: 1200 - Health: 12000", character2.toString());
    }

    @Test
    void testEquals() {
        character2 = character1;
        Character character3;
        assertEquals(character1, character1);
        assertEquals(character2, character2);
        assertEquals(character1, character2);
        assertEquals(character2, character1);
        assertNotEquals(character2, new String());

        try {
            character3 = new Character("Ribbon", 10, Gender.MALE);
            assertEquals(character3, character3);
            assertNotEquals(character1, character3);

            character1 = new Character("Ribbon", 10, Gender.MALE);
            assertEquals(character1, character3);
            character3.setGender(Gender.FEMALE);
            assertNotEquals(character1, character3);

        } catch (CharacterException e) {
            e.printStackTrace();
            fail("testEquals failed");
        }
    }
}