package pl.edu.agh.to2.example.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTest {

    private Weather weather = new Weather();
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
    }

    @Test
    void getAirCondition() {
        weather.setAirCondition(airCondition);
        assertEquals(airCondition, weather.getAirCondition());
    }

    @Test
    void getForecast() {
        weather.setForecast(forecast);
        assertEquals(forecast, weather.getForecast());
    }

    @Test
    void getTemperature() {
        weather.setTemperature(temperature);
        assertEquals(temperature, weather.getTemperature());
    }

    @Test
    void getTemperatureCelsius() {
        weather.setTemperatureCelsius(temperatureCelsius);
        assertEquals(temperatureCelsius, weather.getTemperatureCelsius());
    }

}