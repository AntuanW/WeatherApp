package pl.edu.agh.to2.example.persistance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserConfigurationRepositoryTest {
    @InjectMocks
    private UserConfigurationRepository userConfigurationRepository;
    private UserConfiguration userConfiguration;
    private String userID;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userID = "123";
        userConfiguration = new UserConfiguration(userID);
    }

    @Test
    void testFindByUserId() {
        assertEquals(Optional.empty(), userConfigurationRepository.findByUserId(userID));
        userConfigurationRepository.saveUserConfiguration(userConfiguration);
        assertEquals(Optional.of(userConfiguration), userConfigurationRepository.findByUserId(userID));
    }

    @Test
    void testSaveUserConfiguration() {
        userConfigurationRepository.saveUserConfiguration(userConfiguration);
        assertEquals(Optional.of(userConfiguration), userConfigurationRepository.findByUserId(userID));
    }

}