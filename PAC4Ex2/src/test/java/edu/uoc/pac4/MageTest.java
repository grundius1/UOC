package edu.uoc.pac4;

import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MageTest {

    Mage mage1;
    Mage mage2;

    @BeforeEach
    void init() {
        try {
            Field field = Alliance.class.getDeclaredField("count");
            field.setAccessible(true);
            field.set(null, 0);
            mage1 = new HumanMage("Magico", 15, Gender.MALE);
            mage2 = new HumanMage("Magica", 60, Gender.FEMALE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testMage(){
        assertEquals("Magico", mage1.getName());
        assertEquals(15, mage1.getLevel());
        assertEquals(Gender.MALE, mage1.getGender());
        assertEquals(300, mage1.getStamina());
        assertEquals(3000, mage1.getHealth());
    }

    @Test
    void testGetMana(){
        assertEquals(75, mage1.getMana());
        assertEquals(300, mage2.getMana());
    }

    @Test
    void testSetMana(){
        mage1.setMana(100);
        assertEquals(100, mage1.getMana());

        mage1.setMana();
        assertEquals(75, mage1.getMana());

        assertEquals(75, mage1.calculateMaxMana());

        assertEquals(300, mage2.calculateMaxMana());
    }

    @Test
    void testDecIncMana(){
        assertEquals(75, mage1.getMana());

        mage1.decMana(50);
        assertEquals(25, mage1.getMana());

        mage1.decMana(50);
        assertEquals(0, mage1.getMana());

        mage1.incMana(20);
        assertEquals(20, mage1.getMana());

        mage1.incMana(20);
        assertEquals(40, mage1.getMana());

        mage1.incMana(80);
        assertEquals(75, mage1.getMana());
    }

    @Test
    void testGetAttackPower(){
        assertEquals(300, mage1.getAttackPower());
        assertEquals(1200, mage2.getAttackPower());
    }

    @Test
    void testEat() {
        try {
            Food foodVegetable55 = new Food("carrot", 55, FoodType.VEGETABLE);
            Food water = new Food();

            assertEquals(12000, mage2.getHealth());
            assertEquals(300, mage2.getMana());

            mage2.decHealth(11500);
            assertEquals(500, mage2.getHealth());

            mage2.eat(foodVegetable55);
            assertEquals(11500, mage2.getHealth());

            mage2.decMana(250);
            assertEquals(50, mage2.getMana());

            mage2.eat(water);
            assertEquals(150, mage2.getMana());

            mage2.eat(water);
            assertEquals(250, mage2.getMana());

            mage2.eat(water);
            assertEquals(300, mage2.getMana());

            mage2.eat(water);
            assertEquals(300, mage2.getMana());


        } catch (CharacterException e) {
            fail("testEat failed");
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        final Class<Mage> ownClass = Mage.class;

        //check attribute fields
        assertEquals(1, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("mana").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);


        //check methods, parameters and return types
        assertEquals(7, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getMana").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setMana", int.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("calculateMaxMana").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setMana").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setAttackPower").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("eat", Food.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("attack", Character.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
}
