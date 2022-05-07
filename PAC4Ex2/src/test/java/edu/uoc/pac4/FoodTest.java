package edu.uoc.pac4;

import org.junit.jupiter.api.*;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FoodTest {
    Food food1;
    Food food2;
    Food foodWater;

    @BeforeAll
    void init() {
        try {
            food1 = new Food(" GrilLed MeAt ", 30, FoodType.MEAT);
            food2 = new Food("prawns", 60, FoodType.FISH);
            foodWater = new Food();
        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testFoodException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Food("tomato", 9, FoodType.VEGETABLE));
        assertEquals("[ERROR] Food's level must be in range [10,60]",ex.getMessage());

        ex = assertThrows(IllegalArgumentException.class, () -> new Food("tomato", 61, FoodType.VEGETABLE));
        assertEquals("[ERROR] Food's level must be in range [10,60]",ex.getMessage());
    }

    @Test
    void testGetName() {
        assertEquals("GRILLED_MEAT", food1.getName());
        assertEquals("PRAWNS", food2.getName());
        assertEquals("WATER", foodWater.getName());
    }

    @Test
    void testGetLevel() {
        assertEquals(30, food1.getLevel());
        assertEquals(60, food2.getLevel());
        assertEquals(10, foodWater.getLevel());
    }

    @Test
    void testGetEnergy() {
        assertEquals(6000, food1.getEnergy());
        assertEquals(12000, food2.getEnergy());
        assertEquals(2000, foodWater.getEnergy());
    }

    @Test
    void testToString() {
        assertEquals("(MEAT) GRILLED_MEAT=6000", food1.toString());
        assertEquals("(FISH) PRAWNS=12000", food2.toString());
        assertEquals("(WATER) WATER=2000", foodWater.toString());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        final Class<Food> ownClass = Food.class;

        //check attribute fields
        assertEquals(4, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("name").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("level").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("energy").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("type").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

        //check constructors
        assertEquals(2, ownClass.getDeclaredConstructors().length);


        //check methods, parameters and return types
        assertEquals(5, ownClass.getDeclaredMethods().length);
        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getName").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getLevel").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getEnergy").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getType").getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("toString").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
}
