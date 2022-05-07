package edu.uoc.pac4;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StreamTest {

    //Alliance
    Alliance humanWarrior1, humanWarrior2, gnomeMage1, gnomeMage2, humanMage;

    //Horde
    Horde orc1, orc2, orc3, orc4, orc5, orc6;

    Group allianceGroup;
    Group hordeGroup;

    @BeforeAll
    void init() {
        try {

            //Alliance
            humanWarrior1 = new HumanWarrior("warman", 58, Gender.MALE);
            humanWarrior2 = new HumanWarrior("Lana", 60, Gender.FEMALE);
            gnomeMage1 = new GnomeMage("Jaina", 20, Gender.FEMALE);
            gnomeMage2 = new GnomeMage("Aegwynn", 40, Gender.FEMALE);
            humanMage = new HumanMage("Erbag", 60, Gender.MALE);

            //Horde
            orc1 = new OrcWarrior("orc1", 10, Gender.FEMALE);
            orc2 = new OrcWarrior("orc2", 20, Gender.MALE);
            orc3 = new OrcWarrior("orc3", 30, Gender.FEMALE);
            orc4 = new OrcWarrior("orc4", 40, Gender.MALE);
            orc5 = new OrcWarrior("orc5", 50, Gender.FEMALE);
            orc6 = new OrcWarrior("orc6", 60, Gender.MALE);

            //Groups
            allianceGroup = new Group("Alliance GR", 5);
            hordeGroup = new Group("Horde GR", 10);

            //Add Character in groups
            allianceGroup.add(humanWarrior1);
            allianceGroup.add(humanWarrior2);
            allianceGroup.add(gnomeMage1);
            allianceGroup.add(gnomeMage2);
            allianceGroup.add(humanMage);

            hordeGroup.add(orc1);
            hordeGroup.add(orc2);
            hordeGroup.add(orc3);
            hordeGroup.add(orc4);
            hordeGroup.add(orc5);
            hordeGroup.add(orc6);

        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testGetMagesWithManaGreaterThanAndOrderByName() {
        assertEquals("[Aegwynn, Erbag, Jaina]", allianceGroup.getMagesWithManaGreaterThanAndOrderByName(100).toString());
        assertEquals("[Aegwynn, Erbag]", allianceGroup.getMagesWithManaGreaterThanAndOrderByName(150).toString());
        assertEquals("[Erbag]", allianceGroup.getMagesWithManaGreaterThanAndOrderByName(250).toString());

        assertEquals("[]", hordeGroup.getMagesWithManaGreaterThanAndOrderByName(100).toString());
    }

    @Test
    void testResurrectAllTheDead() {

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        //dead the characters
        humanWarrior2.decHealth(50000);
        humanMage.decHealth(50000);

        allianceGroup.resurrectAllTheDead();
        String result = outputStreamCaptor.toString().trim();

        assertTrue(result.equals("Erbag"+System.lineSeparator()+"Lana") || result.equals("Lana"+System.lineSeparator()+"Erbag"));

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        //dead the characters
        gnomeMage1.decHealth(50000);
        allianceGroup.resurrectAllTheDead();
        assertEquals("Jaina", outputStreamCaptor.toString().trim());
    }

    @Test
    void testAverageLevelGroup() {
        assertEquals(47.6, allianceGroup.averageLevelGroup());
        assertEquals(35.0, hordeGroup.averageLevelGroup());

    }
}
