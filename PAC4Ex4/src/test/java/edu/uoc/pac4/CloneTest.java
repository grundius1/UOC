package edu.uoc.pac4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(Lifecycle.PER_CLASS)

class CloneTest {

    @Test
    void testClone() {
        City c1 = new City("Orgrimar",41.98123, 2.1344, "Horde", 23000);
        try {
            City c2 = (City) c1.clone();
            assertNotEquals(c1, c2);
            assertEquals(c1.getName(),c2.getName());
            assertNotEquals(c1.getLocation(),c2.getLocation());
            assertEquals(c1.getLocation().getLatitude(),c2.getLocation().getLatitude());
            assertEquals(c1.getLocation().getLongitude(),c2.getLocation().getLongitude());
            assertNotEquals(c1.getDetail(),c2.getDetail());
            assertEquals(c1.getDetail().getFaction(),c2.getDetail().getFaction());
            assertEquals(c1.getDetail().getPopulation(),c2.getDetail().getPopulation());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            fail("test failed");
        }
    }
}