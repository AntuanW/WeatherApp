package pl.edu.agh.to2.example.controller.payload;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationRequestTest {
    private static LocationRequest locationRequest;
    private static double latitude;
    private static double longitude;
    private static LocalTime time;

    @BeforeAll
    static void setUp() {
        latitude = 1.0;
        longitude = 2.0;
        time = LocalTime.of(12, 0);
        locationRequest = new LocationRequest(latitude, longitude, time);
    }

    @Test
    void testLatitude() {
        assertEquals(latitude, locationRequest.latitude());
    }

    @Test
    void testLongitude() {
        assertEquals(longitude, locationRequest.longitude());
    }

    @Test
    void testTime() {
        assertEquals(time, locationRequest.time());
    }
}