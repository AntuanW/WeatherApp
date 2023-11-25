package pl.edu.agh.to2.example.weather;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherApiServiceTest {

    private static WeatherApiService weatherApiService;

    @BeforeAll
    static void setUp() throws Exception {
        weatherApiService = new WeatherApiService();
        weatherApiService.setWeatherApiURL("50.0647,19.9450");
        weatherApiService.setWeatherData();
    }

    @Test
    void setWeatherApiURL() {
        weatherApiService.setWeatherApiURL("50.0648,19.9450");
        assertEquals("http://api.weatherapi.com/v1/current.json?key=2ea04a19ce374691987155332232411&q=50.0648,19.9450&aqi=yes", weatherApiService.getWeatherApiURL());
    }

    @Test
    void setWeatherData() {
        assertDoesNotThrow(() -> weatherApiService.setWeatherData());
    }

    @Test
    void getTemperature() {
        System.out.println(weatherApiService.getTemperature());
        assertNotNull(weatherApiService.getTemperature());
    }

    @Test
    void getAirQuality() {
        assertNotNull(weatherApiService.getAirQuality());
    }

    @Test
    void getForecast() {
        assertNotNull(weatherApiService.getForecast());
    }
}