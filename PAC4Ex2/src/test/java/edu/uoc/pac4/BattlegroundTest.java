package edu.uoc.pac4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BattlegroundTest {

    HumanWarrior humanWarrior1, humanWarrior2;
    HumanMage humanMage;
    GnomeMage gnomeMage;
    OrcWarrior orcWarrior, orcNpc;

    @BeforeEach
    void init() {
        try {
            Field field = Alliance.class.getDeclaredField("count");
            field.setAccessible(true);
            field.set(null, 0);

            humanWarrior1 = new HumanWarrior("Atalaya", 45, Gender.FEMALE);
            humanWarrior2 = new HumanWarrior("Will", 55, Gender.MALE);
            humanMage = new HumanMage("Maguita", 60, Gender.FEMALE);
            gnomeMage = new GnomeMage("Maia", 40, Gender.FEMALE);
            orcWarrior = new OrcWarrior("Neo", 60, Gender.MALE);
            orcNpc = new OrcWarrior();
        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testBattle1(){
        try {
            assertEquals(300, humanMage.getMana());
            assertEquals(12000, humanMage.getHealth());
            assertEquals(12000, orcWarrior.getHealth());
            assertEquals(12000, orcNpc.getHealth());

            orcNpc.attack(humanMage);
            assertEquals(8400, humanMage.getHealth());

            orcWarrior.attack(humanMage);
            assertEquals(6600, humanMage.getHealth());

            humanMage.attack(orcNpc);
            assertEquals(249, humanMage.getMana());
            assertEquals(10800, orcNpc.getHealth());

            humanMage.attack(orcWarrior);
            assertEquals(198, humanMage.getMana());
            assertEquals(10800, orcWarrior.getHealth());

        } catch (CharacterException e) {
            e.printStackTrace();
            fail("testBattle1");
        }
    }

    @Test
    void testBattle2(){
        try {
            assertEquals(300, humanMage.getMana());
            assertEquals(12000, orcWarrior.getHealth());

            humanMage.attack(orcWarrior);
            assertEquals(249, humanMage.getMana());
            assertEquals(10800, orcWarrior.getHealth());

            humanMage.attack(orcWarrior);
            assertEquals(198, humanMage.getMana());
            assertEquals(9600, orcWarrior.getHealth());

            humanMage.attack(orcWarrior);
            assertEquals(147, humanMage.getMana());
            assertEquals(8400, orcWarrior.getHealth());

            humanMage.attack(orcWarrior);
            assertEquals(96, humanMage.getMana());
            assertEquals(7200, orcWarrior.getHealth());

            humanMage.attack(orcWarrior);
            assertEquals(45, humanMage.getMana());
            assertEquals(6000, orcWarrior.getHealth());

            Exception ex = assertThrows(CharacterException.class, () -> humanMage.attack(orcWarrior));
            assertEquals("[ERROR] You don't have enough mana", ex.getMessage());

            assertEquals(6000, orcWarrior.getHealth());

        } catch (CharacterException e) {
            e.printStackTrace();
            fail("testBattle2");
        }
    }

    @Test
    void testBattle3(){
        try {
            assertEquals(9000, humanWarrior1.getHealth());

            orcNpc.attack(humanWarrior1);
            assertEquals(5400, humanWarrior1.getHealth());

            orcNpc.attack(humanWarrior1);
            assertEquals(1800, humanWarrior1.getHealth());

            orcNpc.attack(humanWarrior1);
            assertEquals(0, humanWarrior1.getHealth());

            Exception ex = assertThrows(CharacterException.class, () -> orcNpc.attack(humanWarrior1));
            assertEquals("[ERROR] Cannot attack!!", ex.getMessage());

            humanWarrior1.resurrect();
            assertEquals(1350, humanWarrior1.getHealth());

            orcNpc.attack(humanWarrior1);
            assertEquals(0, humanWarrior1.getHealth());

            ex = assertThrows(CharacterException.class, () -> humanWarrior1.attack(orcNpc));
            assertEquals("[ERROR] Cannot attack!!", ex.getMessage());

        } catch (CharacterException e) {
            e.printStackTrace();
            fail("testBattle3");
        }
    }

    @Test
    void testBattle4(){

        Exception ex = assertThrows(CharacterException.class, () -> orcNpc.attack(orcWarrior));
        assertEquals("[ERROR] Cannot attack!!", ex.getMessage());

        ex = assertThrows(CharacterException.class, () -> orcWarrior.attack(orcNpc));
        assertEquals("[ERROR] Cannot attack!!", ex.getMessage());

        ex = assertThrows(CharacterException.class, () -> humanMage.attack(gnomeMage));
        assertEquals("[ERROR] Cannot attack!!", ex.getMessage());

        ex = assertThrows(CharacterException.class, () -> humanWarrior1.attack(gnomeMage));
        assertEquals("[ERROR] Cannot attack!!", ex.getMessage());

    }

    @Test
    void testBattle5(){
        try {
            Food food1 = new Food("CHICKEN", 20, FoodType.MEAT);

            assertEquals(12000, orcWarrior.getHealth());

            for (int i = 0; i < 6; i++){
                gnomeMage.attack(orcWarrior);
            }

            Exception ex = assertThrows(CharacterException.class, () -> gnomeMage.attack(orcWarrior));
            assertEquals("[ERROR] You don't have enough mana", ex.getMessage());

            assertEquals(7200, orcWarrior.getHealth());

            orcWarrior.eat(food1);
            assertEquals(4000, food1.getEnergy());
            assertEquals(11200, orcWarrior.getHealth());

            humanWarrior2.attack(orcWarrior);
            assertEquals(10320, orcWarrior.getHealth());

            orcWarrior.eat(food1);
            assertEquals(12000, orcWarrior.getHealth());

            for (int i = 0; i < 14; i++){
                humanWarrior2.attack(orcWarrior);
            }

            assertEquals(0, orcWarrior.getHealth());

            ex = assertThrows(CharacterException.class, () -> humanWarrior2.attack(orcWarrior));
            assertEquals("[ERROR] Cannot attack!!", ex.getMessage());

        } catch (CharacterException e) {
            e.printStackTrace();
            fail("testBattle5");
        }
    }
}
