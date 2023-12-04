package pl.edu.agh.to2.example.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    private static Location location;

    @BeforeAll
    static void setUp() {
        location = new Location(1.0, 2.0);
    }

    @Test
    void testLatitude() {
        assertEquals(1.0, location.latitude());
    }

    @Test
    void testLongitude() {
        assertEquals(2.0, location.longitude());
    }
}