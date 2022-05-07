package edu.uoc.pac4;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroupTest {

    //Alliance
    Alliance humanWarrior1, humanWarrior2, gnomeMage1, gnomeMage2, humanMage1, humanMage2;

    //Horde
    Horde orc1, orc2, orc3, orc4, orc5, orc6;

    Group allianceGroup1, allianceGroup2;
    Group hordeGroup;

    @BeforeAll
    void init() {
        try {
            //Alliance
            humanWarrior1 = new HumanWarrior("warman", 58, Gender.MALE);
            humanWarrior2 = new HumanWarrior("Lana", 60, Gender.FEMALE);
            gnomeMage1 = new GnomeMage("Jaina", 20, Gender.FEMALE);
            gnomeMage2 = new GnomeMage("Aegwynn", 40, Gender.FEMALE);
            humanMage1 = new HumanMage("Erbag", 60, Gender.MALE);
            humanMage2 = new HumanMage("Erbag2", 60, Gender.MALE);

            //Horde
            orc1 = new OrcWarrior("orc1", 10, Gender.FEMALE);
            orc2 = new OrcWarrior("orc2", 20, Gender.MALE);
            orc3 = new OrcWarrior("orc3", 30, Gender.FEMALE);
            orc4 = new OrcWarrior("orc4", 40, Gender.MALE);
            orc5 = new OrcWarrior("orc5", 50, Gender.FEMALE);
            orc6 = new OrcWarrior("orc6", 60, Gender.MALE);

            //Groups
            allianceGroup1 = new Group("Alliance GR1", 5);
            allianceGroup2 = new Group("Alliance GR2", 5);
            hordeGroup = new Group("Horde GR", 10);

        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    @Order(1)
    void testConstructor() {
        assertEquals(5, allianceGroup1.getSize());
        assertEquals(5, allianceGroup2.getSize());
        assertEquals(10, hordeGroup.getSize());

        assertEquals("Alliance GR1", allianceGroup1.getName());
        assertEquals("Alliance GR2", allianceGroup2.getName());
        assertEquals("Horde GR", hordeGroup.getName());

        assertNotNull(allianceGroup1.getCharacters());
        assertNotNull(allianceGroup2.getCharacters());
        assertNotNull(hordeGroup.getCharacters());

    }

    @Test
    @Order(2)
    void testSizeException() {
        Exception ex = assertThrows(Exception.class, () -> new Group("Test GR", 9));
        assertEquals("[ERROR] Group's size must be 5 or 10", ex.getMessage());
    }


    @Test
    @Order(3)
    void testAddCharacter1() {
        assertEquals(0, allianceGroup1.getCharacters().size());
        assertEquals(0, allianceGroup2.getCharacters().size());
        assertEquals(0, hordeGroup.getCharacters().size());

        assertTrue(allianceGroup1.add(humanMage1));
        assertEquals(1, allianceGroup1.getCharacters().size());

        assertTrue(allianceGroup2.add(humanMage1));
        assertEquals(1, allianceGroup2.getCharacters().size());

        assertTrue(allianceGroup1.add(humanWarrior1));
        assertEquals(2, allianceGroup1.getCharacters().size());

        assertTrue(hordeGroup.add(orc1));
        assertEquals(1, hordeGroup.getCharacters().size());

        assertTrue(hordeGroup.add(orc2));
        assertEquals(2, hordeGroup.getCharacters().size());

        assertTrue(hordeGroup.add(orc3));
        assertEquals(3, hordeGroup.getCharacters().size());

        assertTrue(hordeGroup.add(orc4));
        assertEquals(4, hordeGroup.getCharacters().size());

        assertTrue(hordeGroup.add(orc5));
        assertEquals(5, hordeGroup.getCharacters().size());
    }

    @Test
    @Order(4)
    void testAddCharacter2() {
        assertEquals(2, allianceGroup1.getCharacters().size());
        assertEquals(1, allianceGroup2.getCharacters().size());
        assertEquals(5, hordeGroup.getCharacters().size());

        assertTrue(allianceGroup1.add(humanWarrior2));
        assertEquals(3, allianceGroup1.getCharacters().size());

        assertFalse(allianceGroup1.add(humanMage1)); //repeated, humanMage1 is already in the group
        assertEquals(3, allianceGroup1.getCharacters().size());

        assertTrue(allianceGroup1.add(gnomeMage1));
        assertEquals(4, allianceGroup1.getCharacters().size());

        assertFalse(allianceGroup1.add(humanWarrior2)); //repeated, humanWarrior2 is already in the group
        assertEquals(4, allianceGroup1.getCharacters().size());

        assertTrue(allianceGroup1.add(gnomeMage2));
        assertEquals(5, allianceGroup1.getCharacters().size());

        assertFalse(allianceGroup1.add(humanMage2)); //size = 5, no more characters can be added to the group
        assertEquals(5, allianceGroup1.getCharacters().size());

        assertFalse(hordeGroup.add(orc1)); //repeated, orc1 is already in the group
        assertEquals(5, hordeGroup.getCharacters().size());

        assertTrue(hordeGroup.add(orc6));
        assertEquals(6, hordeGroup.getCharacters().size());
    }

    @Test
    @Order(5)
    void testAddCharacter7() {
        assertEquals(5, allianceGroup1.getCharacters().size());
        assertEquals(1, allianceGroup2.getCharacters().size());
        assertEquals(6, hordeGroup.getCharacters().size());

        Exception ex = assertThrows(Exception.class, () ->	allianceGroup1.add(null));
        assertEquals("[ERROR] Character object cannot be null", ex.getMessage());
    }

    @Test
    @Order(6)
    void testExists1() {
        assertTrue(allianceGroup1.exists(humanMage1));
        assertFalse(allianceGroup1.exists(humanMage2));
        assertTrue(hordeGroup.exists(orc3));
        assertFalse(allianceGroup2.exists(humanWarrior1));
    }

    @Test
    @Order(7)
    void testEmpty1() {
        assertFalse(allianceGroup1.isEmpty());
        assertFalse(allianceGroup2.isEmpty());
        assertFalse(hordeGroup.isEmpty());
    }

    @Test
    @Order(8)
    void testRemove() {
        assertEquals(5, allianceGroup1.getCharacters().size());
        assertEquals(1, allianceGroup2.getCharacters().size());
        assertEquals(6, hordeGroup.getCharacters().size());

        assertTrue(allianceGroup1.remove(humanMage1));
        assertEquals(4, allianceGroup1.getCharacters().size());

        assertTrue(allianceGroup1.remove(humanWarrior2));
        assertEquals(3, allianceGroup1.getCharacters().size());

        assertFalse(allianceGroup1.remove(humanMage2)); //not exists in group
        assertEquals(3, allianceGroup1.getCharacters().size());

        Exception ex = assertThrows(Exception.class, () ->	allianceGroup1.remove(null));
        assertEquals("[ERROR] Character object cannot be null", ex.getMessage());
        assertEquals(3, allianceGroup1.getCharacters().size());

        assertTrue(allianceGroup2.remove(humanMage1));
        assertEquals(0, allianceGroup2.getCharacters().size());

        assertFalse(allianceGroup2.remove(gnomeMage2)); //not exists in group
        assertEquals(0, allianceGroup2.getCharacters().size());
    }

    @Test
    @Order(9)
    void testToString() {
        assertEquals("Group: Alliance GR1 - Size: 5 - Characters: 3", allianceGroup1.toString());
        assertEquals("Group: Alliance GR2 - Size: 5 - Characters: 0", allianceGroup2.toString());
        assertEquals("Group: Horde GR - Size: 10 - Characters: 6", hordeGroup.toString());
    }

    @Test
    @Order(10)
    void testRemoveAll() {
        assertEquals(3, allianceGroup1.getCharacters().size());
        assertEquals(0, allianceGroup2.getCharacters().size());
        assertEquals(6, hordeGroup.getCharacters().size());

        allianceGroup1.remove();
        assertEquals(0, allianceGroup1.getCharacters().size());

        hordeGroup.remove();
        assertEquals(0, hordeGroup.getCharacters().size());
    }

    @Test
    @Order(11)
    void testExists2() {
        assertFalse(allianceGroup1.exists(humanMage1));
        assertFalse(allianceGroup1.exists(humanMage2));
        assertFalse(hordeGroup.exists(orc3));
        assertFalse(allianceGroup2.exists(humanWarrior1));
    }

    @Test
    @Order(12)
    void testEmpty2() {
        assertTrue(allianceGroup1.isEmpty());
        assertTrue(allianceGroup2.isEmpty());
        assertTrue(hordeGroup.isEmpty());
    }
}
