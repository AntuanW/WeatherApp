package pl.edu.agh.to2.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureServiceTest {

    @InjectMocks
    private TemperatureService temperatureService;

    private double temperature;
    private double windSpeedKmPerHour;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        temperature = 10.0;
        windSpeedKmPerHour = 5.0;
    }

    @Test
    void testCalculateSensedTemperature() {
        double sensedTemperature = temperatureService.calculateSensedTemperature(temperature, windSpeedKmPerHour);
        double expectedSensedTemperature = 9.755;
        assertEquals(expectedSensedTemperature, sensedTemperature, 0.01);
    }
}
