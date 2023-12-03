package pl.edu.agh.to2.example.persistance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserConfigurationRepositoryTest {

    private UserConfigurationRepository userConfigurationRepository;
    private UserConfiguration userConfiguration;
    private String userID;

    @BeforeEach
    void setUp() {
        userConfigurationRepository = new UserConfigurationRepository();
        userID = "123";
        userConfiguration = new UserConfiguration(userID);

    }

    @Test
    void findByUserId() {
        assertEquals(Optional.empty(), userConfigurationRepository.findByUserId(userID));
        userConfigurationRepository.saveUserConfiguration(userConfiguration);
        assertEquals(Optional.of(userConfiguration), userConfigurationRepository.findByUserId(userID));
    }

}