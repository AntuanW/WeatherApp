package pl.edu.agh.to2.example.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;

import static org.junit.jupiter.api.Assertions.*;

class WeatherRequestTest {

    private static WeatherRequest weatherRequest;

    @BeforeAll
    static void setUp() {
        weatherRequest = new WeatherRequest(0.0, AirCondition.GOOD, Forecast.FOGGY);
    }

    @Test
    void temperature() {
        assertEquals(0.0, weatherRequest.temperature());
    }

    @Test
    void airQuality() {
        assertEquals(AirCondition.GOOD, weatherRequest.airQuality());
    }

    @Test
    void forecast() {
        assertEquals(Forecast.FOGGY, weatherRequest.forecast());
    }
}