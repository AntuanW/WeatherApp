package pl.edu.agh.to2.example.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.utils.LocationRequest;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {

    private static WeatherService weatherService;

    @BeforeEach
    void setUp(){
        weatherService = new WeatherService();
        try {
            weatherService.setWeatherData(new LocationRequest(50.0, 50.0));
        } catch (Exception e) {
            System.out.println("Something went wrong with getting weather data.");
        }
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
    void getRightWardrobe() {
        assertNotNull(weatherService.getRightWardrobe());
    }
}