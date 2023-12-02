package pl.edu.agh.to2.example.weather.measures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForecastTest {

    private Forecast clearForecast;
    private Forecast cloudyForecast;
    private Forecast foggyForecast;
    private Forecast thunderyForecast;
    private Forecast rainyForecast;
    private Forecast sleetyForecast;
    private Forecast snowyForecast;
    private Forecast hailForecast;

    @BeforeEach
    void setUp() {
        clearForecast = Forecast.CLEAR;
        cloudyForecast = Forecast.CLOUDY;
        foggyForecast = Forecast.FOGGY;
        thunderyForecast = Forecast.THUNDERY;
        rainyForecast = Forecast.RAINY;
        sleetyForecast = Forecast.SLEETY;
        snowyForecast = Forecast.SNOWY;
        hailForecast = Forecast.HAIL;
    }

    @Test
    void getClearForecast() {
        assertEquals(clearForecast, Forecast.getForecast("Sunny"));
        assertEquals(clearForecast, Forecast.getForecast("Clear"));
    }

    @Test
    void getSnowyForecast(){
        assertEquals(snowyForecast, Forecast.getForecast("Patchy light snow with thunder"));
        assertEquals(snowyForecast, Forecast.getForecast("Moderate or heavy snow with thunder"));
        assertEquals(snowyForecast, Forecast.getForecast("Light snow showers"));
        assertEquals(snowyForecast, Forecast.getForecast("Moderate or heavy snow showers"));
        assertEquals(snowyForecast, Forecast.getForecast("Patchy light snow"));
        assertEquals(snowyForecast, Forecast.getForecast("Light snow"));
        assertEquals(snowyForecast, Forecast.getForecast("Patchy moderate snow"));
        assertEquals(snowyForecast, Forecast.getForecast("Moderate snow"));
        assertEquals(snowyForecast, Forecast.getForecast("Patchy heavy snow"));
        assertEquals(snowyForecast, Forecast.getForecast("Heavy snow"));
        assertEquals(snowyForecast, Forecast.getForecast("Blowing snow"));
        assertEquals(snowyForecast, Forecast.getForecast("Blizzard"));
        assertEquals(snowyForecast, Forecast.getForecast("Patchy snow possible"));
    }

    @Test
    void getRainyForecast(){
        assertEquals(rainyForecast, Forecast.getForecast("Patchy light rain with thunder"));
        assertEquals(rainyForecast, Forecast.getForecast("Moderate or heavy rain with thunder"));
        assertEquals(rainyForecast, Forecast.getForecast("Light rain shower"));
        assertEquals(rainyForecast, Forecast.getForecast("Moderate or heavy rain shower"));
        assertEquals(rainyForecast, Forecast.getForecast("Torrential rain shower"));
        assertEquals(rainyForecast, Forecast.getForecast("Patchy light drizzle"));
        assertEquals(rainyForecast, Forecast.getForecast("Light drizzle"));
        assertEquals(rainyForecast, Forecast.getForecast("Freezing drizzle"));
        assertEquals(rainyForecast, Forecast.getForecast("Heavy freezing drizzle"));
        assertEquals(rainyForecast, Forecast.getForecast("Patchy light rain"));
        assertEquals(rainyForecast, Forecast.getForecast("Light rain"));
        assertEquals(rainyForecast, Forecast.getForecast("Moderate rain at times"));
        assertEquals(rainyForecast, Forecast.getForecast("Moderate rain"));
        assertEquals(rainyForecast, Forecast.getForecast("Heavy rain at times"));
        assertEquals(rainyForecast, Forecast.getForecast("Heavy rain"));
        assertEquals(rainyForecast, Forecast.getForecast("Light freezing rain"));
        assertEquals(rainyForecast, Forecast.getForecast("Moderate or heavy freezing rain"));
        assertEquals(rainyForecast, Forecast.getForecast("Patchy freezing drizzle possible"));
        assertEquals(rainyForecast, Forecast.getForecast("Patchy rain possible"));
    }

    @Test
    void getHailForecast(){
        assertEquals(hailForecast, Forecast.getForecast("Light showers of ice pellets"));
        assertEquals(hailForecast, Forecast.getForecast("Moderate or heavy showers of ice pellets"));
        assertEquals(hailForecast, Forecast.getForecast("Ice pellets"));
    }

    @Test
    void getCloudyForecast(){
        assertEquals(cloudyForecast, Forecast.getForecast("Partly cloudy"));
        assertEquals(cloudyForecast, Forecast.getForecast("Cloudy"));
        assertEquals(cloudyForecast, Forecast.getForecast("Overcast"));
    }

    @Test
    void getFogyForecast(){
        assertEquals(foggyForecast, Forecast.getForecast("Mist"));
        assertEquals(foggyForecast, Forecast.getForecast("Fog"));
        assertEquals(foggyForecast, Forecast.getForecast("Freezing fog"));
    }

    @Test
    void getSleetyForecast(){
        assertEquals(sleetyForecast, Forecast.getForecast("Light sleet"));
        assertEquals(sleetyForecast, Forecast.getForecast("Moderate or heavy sleet"));
        assertEquals(sleetyForecast, Forecast.getForecast("Light sleet showers"));
        assertEquals(sleetyForecast, Forecast.getForecast("Moderate or heavy sleet showers"));
        assertEquals(sleetyForecast, Forecast.getForecast("Patchy sleet possible"));
    }

    @Test
    void getThunderyForecast(){
        assertEquals(thunderyForecast, Forecast.getForecast("Thundery outbreaks possible"));
    }
}