package pl.edu.agh.to2.example.controller.payload;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LocationRequestTest {
    private static LocationRequest dualLocationRequest;
    private static LocationRequest singleLocationRequest;

    @BeforeAll
    static void setUp() {
        dualLocationRequest = new LocationRequest(1.0, 2.0, Optional.of(3.0), Optional.of(4.0));
        singleLocationRequest = new LocationRequest(1.0, 2.0, null, null);
    }

    @Test
    void testLatitude() {
        assertEquals(1.0, dualLocationRequest.latitude());
        assertEquals(3.0, dualLocationRequest.latitude2().get());
        assertEquals(1.0, singleLocationRequest.latitude());
        assertEquals(Optional.empty(), singleLocationRequest.latitude2());
    }

    @Test
    void testLongitude() {
        assertEquals(2.0, dualLocationRequest.longitude());
        assertEquals(4.0, dualLocationRequest.longitude2().get());
        assertEquals(2.0, singleLocationRequest.longitude());
        assertEquals(Optional.empty(), singleLocationRequest.longitude2());
    }
}