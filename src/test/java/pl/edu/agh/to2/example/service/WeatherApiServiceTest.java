package pl.edu.agh.to2.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.to2.example.model.Location;

import static org.junit.jupiter.api.Assertions.*;

class WeatherApiServiceTest {

    @InjectMocks
    private WeatherApiService weatherApiService;

    private Location location;

    @BeforeEach
    void setUp() {
        location = new Location(50.0619, 19.9367, null, null);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWeatherData() {
        assertNotNull(weatherApiService.getWeatherData(location.latitude(), location.longitude()));
    }
}