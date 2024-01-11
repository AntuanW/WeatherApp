package pl.edu.agh.to2.example.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherTest {

    private final Weather weather = new Weather();
    private String locationName;
    private AirCondition airCondition;
    private Forecast forecast;
    private double temperatureCelsius;
    private Temperature temperature;

    @BeforeEach
    void setUp() {
        airCondition = AirCondition.GOOD;
        forecast = Forecast.FOGGY;
        temperatureCelsius = 0.0;
        temperature = Temperature.COLD;
        locationName = "Mumbai";
    }

    @Test
    void testGetAirCondition() {
        weather.setAirCondition(airCondition);
        assertEquals(airCondition, weather.getAirCondition());
    }

    @Test
    void testGetForecast() {
        weather.setForecast(forecast);
        assertEquals(forecast, weather.getForecast());
    }

    @Test
    void testGetTemperature() {
        weather.setTemperature(temperature);
        assertEquals(temperature, weather.getTemperature());
    }

    @Test
    void testGetTemperatureCelsius() {
        weather.setTemperatureCelsius(temperatureCelsius);
        assertEquals(temperatureCelsius, weather.getTemperatureCelsius());
    }

    @Test
    void testGetLocationName() {
        weather.setLocationName(locationName);
        assertEquals(locationName, weather.getLocationName());
    }
}