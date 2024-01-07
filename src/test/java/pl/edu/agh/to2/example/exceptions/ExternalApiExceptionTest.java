package pl.edu.agh.to2.example.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.model.Location;
import pl.edu.agh.to2.example.service.WeatherApiService;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExternalApiExceptionTest {

    private String errorMessage;
    private ExternalApiException exception;
    private WeatherApiService weatherApiService;
    private Location location;

    @BeforeEach
    void setUp() {
        errorMessage = "Custom error message";
        exception = new ExternalApiException(errorMessage);
        LocalTime time = LocalTime.of(21, 37);
        location = new Location(0.0, 0.0, time);
        weatherApiService = mock(WeatherApiService.class);
        when(weatherApiService.getWeatherData(location)).thenThrow(exception);
    }

    @Test
    void testExceptionWithMessage() {
        assertEquals(errorMessage, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testExternalApiException() {
        assertThrows(ExternalApiException.class, () -> {
            weatherApiService.getWeatherData(location);
        });
    }
}