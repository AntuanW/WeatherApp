package pl.edu.agh.to2.example.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    private static Location location;

    @BeforeAll
    static void setUp() {
        location = new Location(1.0, 2.0, Optional.of(3.0), Optional.of(4.0));
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
    void testLatitude2(){
        assertEquals(Optional.of(3.0), location.latitude2());
    }

    @Test
    void testLongitude2(){
        assertEquals(Optional.of(4.0), location.longitude2());
    }
}