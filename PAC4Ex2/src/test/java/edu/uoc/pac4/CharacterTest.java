package edu.uoc.pac4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CharacterTest {

    HumanWarrior character1;
    HumanWarrior character2;

    @BeforeEach
    void init() {
        try {
            character1 = new HumanWarrior("Tyrande", 27, Gender.FEMALE);
            character2 = new HumanWarrior("Anduin", 60, Gender.MALE);
        } catch (Exception e) {
            e.printStackTrace();
            fail("init");
        }
    }

    @Test
    void testCharacter() {
        assertEquals("Tyrande", character1.getName());
        assertEquals(27, character1.getLevel());
        assertEquals(Gender.FEMALE, character1.getGender());
        assertEquals(540, character1.getStamina());
        assertEquals(5400, character1.getHealth());
    }



    @Test
    void testGetAttackPower() {
        assertEquals(432, character1.getAttackPower());
        assertEquals(960, character2.getAttackPower());
    }

    @Test
    void testSetAttackPower() {
        try{
            //When level change, setLevel calculate the new stamina and health
            //but no the strength and attackPower, therefore we have call the setters methods
            //to update the attackPower
            character1.setLevel(59);

            //call to update the strength and attackPower
            character1.setStrength();
            character1.setAttackPower();

            assertEquals(944, character1.getAttackPower());
        }catch(Exception e) {
            fail("setName failed");
        }
    }

    @Test
    void testCompareTo() {

        try {
            Character [] characters = new Character[8];

            //Alliance Characters
            HumanWarrior hw1 = new HumanWarrior("Harry", 12, Gender.MALE);
            HumanWarrior hw2 = new HumanWarrior("Archimode", 58, Gender.FEMALE);
            GnomeMage gm = new GnomeMage("Lord", 45, Gender.MALE);
            HumanMage hm = new HumanMage("Duna", 60, Gender.FEMALE);
            //Horde Character Products
            OrcWarrior orc1 = new OrcWarrior("Thrall", 20, Gender.MALE);
            OrcWarrior orc2 = new OrcWarrior("Len", 59, Gender.MALE);
            OrcWarrior orc3 = new OrcWarrior("Aitan", 20, Gender.FEMALE);
            OrcWarrior orc4 = new OrcWarrior("Morgana", 19, Gender.FEMALE);

            characters[0] = hw1;
            characters[1] = hw2;
            characters[2] = gm;
            characters[3] = hm;
            characters[4] = orc1;
            characters[5] = orc2;
            characters[6] = orc3;
            characters[7] = orc4;

            /*
              NOTAS compareTo: int compareTo(Character character)
              devuelve < 0, entonces el character que llama al metodo va primero.
              devuelve == 0 entonces son iguales.
              devuelve > 0, entonces el parametro pasado al metodo compareTo va primero.
             */

            Exception ex = assertThrows(NullPointerException.class, () -> hw1.compareTo(null));
            assertEquals(NullPointerException.class, ex.getClass());
            assertTrue(hw2.compareTo(hw1) > 0); //positivo porque level 58 (hw2) va despues que level 12 (hw1)
            assertTrue(orc1.compareTo(orc2) < 0); //negativo porque 20 (orco1) va antes que level 59 (59)
            assertTrue(orc1.compareTo(orc3) > 0); // level iguales 20=20, pero positivo porque name de orc1 va después que name de orco3
            assertTrue(gm.compareTo(gm) == 0);

            Arrays.sort(characters);
            assertArrayEquals(new Character[]{hw1, orc4, orc3, orc1, gm, hw2, orc2, hm}, characters);

        } catch (CharacterException e) {
            e.printStackTrace();
            fail("testCompareTo failed");
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        final Class<Character> ownClass = Character.class;

        //check attribute fields
        assertEquals(7, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("id").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("name").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("level").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("stamina").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("health").getModifiers()));
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("gender").getModifiers()));
            assertTrue(Modifier.isProtected(ownClass.getDeclaredField("attackPower").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);


        //check methods, parameters and return types
        assertEquals(26, ownClass.getDeclaredMethods().length);
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
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("compareTo", Character.class).getModifiers()));
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getAttackPower").getModifiers()));
            assertTrue(Modifier.isAbstract(ownClass.getDeclaredMethod("warsong").getModifiers()));
            assertTrue(Modifier.isAbstract(ownClass.getDeclaredMethod("getMainCity").getModifiers()));
            assertTrue(Modifier.isAbstract(ownClass.getDeclaredMethod("eat", Food.class).getModifiers()));
            assertTrue(Modifier.isAbstract(ownClass.getDeclaredMethod("setAttackPower").getModifiers()));


        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }

    }

}