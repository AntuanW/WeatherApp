package pl.edu.agh.to2.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.model.Location;

import static org.junit.jupiter.api.Assertions.*;

class WeatherApiServiceTest {

    private WeatherApiService weatherApiService;
    private Location location;

    @BeforeEach
    void setUp() {
        weatherApiService = new WeatherApiService();
        location = new Location(0.0, 0.0);
    }

    @Test
    void getWeatherData() {
        assertNotNull(weatherApiService.getWeatherData(location));
    }
}