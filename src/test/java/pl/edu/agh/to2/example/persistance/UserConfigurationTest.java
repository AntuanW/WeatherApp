package pl.edu.agh.to2.example.persistance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.model.Location;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserConfigurationTest {
    private UserConfiguration userConfiguration;
    private String userId;
    private Location location;

    @BeforeEach
    void setUp() {
        userId = "123";
        location = new Location(0.0, 0.0);
        userConfiguration = new UserConfiguration(userId);
    }

    @Test
    void testGetLocation() {
        assertEquals(Optional.empty(), userConfiguration.getLocation());
        userConfiguration.setLocation(location);
        assertEquals(Optional.of(location), userConfiguration.getLocation());
    }

    @Test
    void testGetUserId() {
        assertEquals(userId, userConfiguration.getUserId());
    }
}