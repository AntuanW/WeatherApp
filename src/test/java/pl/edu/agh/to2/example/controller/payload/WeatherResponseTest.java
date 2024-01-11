package pl.edu.agh.to2.example.controller.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherResponseTest {
    private WeatherResponse weatherResponse;
    private String locationName;
    private double temperature;
    private AirCondition airQuality;
    private Forecast forecast;
    private Temperature temperatureScale;

    @BeforeEach
    void setUp() {
        locationName = "Krakow";
        temperature = 0.0;
        airQuality = AirCondition.GOOD;
        forecast = Forecast.CLEAR;
        temperatureScale = Temperature.COLD;
        weatherResponse = new WeatherResponse(locationName, temperature, airQuality, forecast, temperatureScale);
    }

    @Test
    void testLocationName() {
        assertEquals(locationName, weatherResponse.locationName());
    }

    @Test
    void testTemperature() {
        assertEquals(temperature, weatherResponse.temperature());
    }

    @Test
    void testAirQuality() {
        assertEquals(airQuality, weatherResponse.airQuality());
    }

    @Test
    void testForecast() {
        assertEquals(forecast, weatherResponse.forecast());
    }

    @Test
    void testTemperatureScale() {
        assertEquals(temperatureScale, weatherResponse.temperatureScale());
    }
}