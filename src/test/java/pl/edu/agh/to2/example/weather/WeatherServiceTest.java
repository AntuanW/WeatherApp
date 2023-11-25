package pl.edu.agh.to2.example.weather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class WeatherServiceTest {

    private static WeatherService weatherService;

    @BeforeAll
    static void setUp() {
        weatherService = new WeatherService();
        weatherService.setWeatherData("50.0647,19.9450");
    }

    @Test
    void setWeatherData() {
        assertDoesNotThrow(() -> weatherService.setWeatherData("50.0647,19.9450"));
    }

    @Test
    void getWardrobe() {
        assertNotNull(weatherService.getWardrobe());
    }

    @Test
    void getTemperature() {
        assertNotNull(weatherService.getTemperature());
    }

    @Test
    void getForecast() {
        assertNotNull(weatherService.getForecast());
    }

    @Test
    void getAirCondition() {
        assertNotNull(weatherService.getAirCondition());
    }
}