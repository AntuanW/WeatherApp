package pl.edu.agh.to2.example.controller.payload;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationRequestTest {
    private static LocationRequest locationRequest;

    @BeforeAll
    static void setUp() {
        locationRequest = new LocationRequest(1.0, 2.0);
    }

    @Test
    void testLatitude() {
        assertEquals(1.0, locationRequest.latitude());
    }

    @Test
    void testLongitude() {
        assertEquals(2.0, locationRequest.longitude());
    }
}