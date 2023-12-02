package pl.edu.agh.to2.example.controller.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import static org.junit.jupiter.api.Assertions.*;

class WeatherResponseTest {

    private WeatherResponse weatherResponse;
    private double temperature;
    private AirCondition airQuality;
    private Forecast forecast;
    private Temperature temperatureScale;

    @BeforeEach
    void setUp() {
        temperature = 0.0;
        airQuality = AirCondition.GOOD;
        forecast = Forecast.CLEAR;
        temperatureScale = Temperature.COLD;
        weatherResponse = new WeatherResponse(temperature, airQuality, forecast, temperatureScale);
    }

    @Test
    void temperature() {
        assertEquals(temperature, weatherResponse.temperature());
    }

    @Test
    void airQuality() {
        assertEquals(airQuality, weatherResponse.airQuality());
    }

    @Test
    void forecast() {
        assertEquals(forecast, weatherResponse.forecast());
    }

    @Test
    void temperatureScale() {
        assertEquals(temperatureScale, weatherResponse.temperatureScale());
    }
}