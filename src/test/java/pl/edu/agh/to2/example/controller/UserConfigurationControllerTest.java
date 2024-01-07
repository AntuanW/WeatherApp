package pl.edu.agh.to2.example.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.edu.agh.to2.example.controller.payload.LocationRequest;
import pl.edu.agh.to2.example.controller.payload.UserResponse;
import pl.edu.agh.to2.example.persistance.UserConfiguration;
import pl.edu.agh.to2.example.persistance.UserConfigurationRepository;

import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.*;

class UserConfigurationControllerTest {
    @InjectMocks
    private UserConfigurationController userConfigurationController;

    @Mock
    private UserConfigurationRepository userConfigurationRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInitializeUser() {
        when(userConfigurationRepository.findByUserId(anyString())).thenReturn(java.util.Optional.empty());

        ResponseEntity<UserResponse> response = userConfigurationController.initializeUser();

        verify(userConfigurationRepository, times(1)).findByUserId(anyString());
        verify(userConfigurationRepository, times(1)).saveUserConfiguration(any(UserConfiguration.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testPostLocationWithSuccess() {
        String userToken = "testToken";
        LocationRequest locationRequest = new LocationRequest(10.0, 20.0, LocalTime.of(20, 0));
        UserConfiguration userConfiguration = new UserConfiguration(userToken);

        when(userConfigurationRepository.findByUserId(userToken)).thenReturn(java.util.Optional.of(userConfiguration));

        ResponseEntity<String> response = userConfigurationController.postLocation(userToken, List.of(locationRequest));

        verify(userConfigurationRepository, times(1)).findByUserId(userToken);
        verify(userConfigurationRepository, times(1)).saveUserConfiguration(userConfiguration);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User location successfully saved", response.getBody());
    }

    @Test
    void testPostLocationWithUserNotFound() {
        String userToken = "User not found";
        LocationRequest locationRequest = new LocationRequest(10.0, 20.0, LocalTime.of(20, 0));

        when(userConfigurationRepository.findByUserId(userToken)).thenReturn(java.util.Optional.empty());
        when(userConfigurationRepository.findByUserId(userToken)).thenReturn(java.util.Optional.empty());

        ResponseEntity<String> response = userConfigurationController.postLocation(userToken, List.of(locationRequest));

        verify(userConfigurationRepository, times(1)).findByUserId(userToken);
        verify(userConfigurationRepository, never()).saveUserConfiguration(any(UserConfiguration.class));
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("User " + userToken + " not found", response.getBody());
    }

    @Test
    void testPostLocationWithException() {
        String userToken = "testToken";
        LocationRequest locationRequest = new LocationRequest(10.0, 20.0, LocalTime.of(20, 0));

        when(userConfigurationRepository.findByUserId(userToken)).thenThrow(new RuntimeException("Test Exception"));

        ResponseEntity<String> response = userConfigurationController.postLocation(userToken, List.of(locationRequest));

        verify(userConfigurationRepository, times(1)).findByUserId(userToken);
        verify(userConfigurationRepository, never()).saveUserConfiguration(any(UserConfiguration.class));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Something went wrong with posting location.", response.getBody());
    }
}
