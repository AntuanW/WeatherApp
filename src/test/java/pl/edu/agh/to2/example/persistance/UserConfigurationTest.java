package pl.edu.agh.to2.example.persistance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.model.Location;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserConfigurationTest {
    private UserConfiguration userConfiguration;
    private String userId;
    private List<Location> locations;

    @BeforeEach
    void setUp() {
        LocalTime time1 = LocalTime.of(14, 20);
        LocalTime time2 = LocalTime.of(18, 19);
        userId = "123";
        Location location1 = new Location(0.0, 0.0, time1);
        Location location2 = new Location(51.51, -0.11, time2);
        locations = List.of(location1, location2);
        userConfiguration = new UserConfiguration(userId);
    }

    @Test
    void testGetLocations() {
        assertNull(userConfiguration.getLocations());
        userConfiguration.setLocations(locations);
        assertEquals(locations, userConfiguration.getLocations());
    }

    @Test
    void testGetUserId() {
        assertEquals(userId, userConfiguration.getUserId());
    }
}