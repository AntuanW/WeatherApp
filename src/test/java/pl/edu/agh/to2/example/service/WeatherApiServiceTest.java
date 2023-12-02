package pl.edu.agh.to2.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.service.WeatherApiService;

import static org.junit.jupiter.api.Assertions.*;

class WeatherApiServiceTest {

    private static WeatherApiService weatherApiService;

    @BeforeAll
    static void setUp() {
        weatherApiService = new WeatherApiService();
        weatherApiService.setWeatherApiURL("50.0647,19.9450");
    }

    @Test
    void setWeatherApiURL() {
        weatherApiService.setWeatherApiURL("50.0648,19.9450");
        assertEquals("http://api.weatherapi.com/v1/current.json?key=2ea04a19ce374691987155332232411&q=50.0648,19.9450&aqi=yes", weatherApiService.getWeatherApiURL());
    }

    @Test
    void getWeatherData() throws Exception {
        JsonNode weatherData = weatherApiService.getWeatherData();
        assertNotNull(weatherData);
    }
}