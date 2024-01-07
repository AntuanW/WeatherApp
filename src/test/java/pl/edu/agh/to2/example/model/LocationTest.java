package pl.edu.agh.to2.example.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    private static Location location;

    @BeforeAll
    static void setUp() {
        LocalTime time = LocalTime.of(16, 0);
        location = new Location(1.0, 2.0, time);
    }

    @Test
    void testLatitude() {
        assertEquals(1.0, location.latitude());
    }

    @Test
    void testLongitude() {
        assertEquals(2.0, location.longitude());
    }

    @Test
    void testTime(){
        assertEquals(LocalTime.of(16, 0), location.time());
    }
}