package pl.edu.agh.to2.example.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.utils.LocationRequest;
import pl.edu.agh.to2.example.wardrobe.Wardrobe;
import pl.edu.agh.to2.example.weather.measures.AirCondition;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {

    private static WeatherService weatherService;

    @BeforeEach
    void setUp() {
        weatherService = new WeatherService();
        weatherService.setWeatherData(new LocationRequest(50.0, 50.0));
        weatherService.setWardrobe();
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

    @Test
    void getTempCelsius() {
        assertNotNull(weatherService.getTempCelsius());
    }

    @Test
    void getRightWardrobe() {
        assertNotNull(weatherService.getRightWardrobe());
    }
}