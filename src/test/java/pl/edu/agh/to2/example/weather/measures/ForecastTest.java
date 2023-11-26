package pl.edu.agh.to2.example.weather.measures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForecastTest {

    private static Forecast clearForecast;
    private static Forecast cloudyForecast;
    private static Forecast foggyForecast;
    private static Forecast thunderyForecast;
    private static Forecast rainyForecast;
    private static Forecast sleetyForecast;
    private static Forecast snowyForecast;
    private static Forecast hailForecast;

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
    void checkIfUmbrella() {
        assertFalse(clearForecast.checkIfUmbrella());
        assertFalse(cloudyForecast.checkIfUmbrella());
        assertFalse(foggyForecast.checkIfUmbrella());
        assertFalse(thunderyForecast.checkIfUmbrella());
        assertTrue(rainyForecast.checkIfUmbrella());
        assertTrue(sleetyForecast.checkIfUmbrella());
        assertTrue(snowyForecast.checkIfUmbrella());
        assertTrue(hailForecast.checkIfUmbrella());
    }

    @Test
    void getForecast() {
        assertEquals(clearForecast, Forecast.getForecast("Sunny"));
        assertEquals(clearForecast, Forecast.getForecast("Clear"));
        assertEquals(cloudyForecast, Forecast.getForecast("Partly cloudy"));
        assertEquals(cloudyForecast, Forecast.getForecast("Cloudy"));
        assertEquals(cloudyForecast, Forecast.getForecast("Overcast"));
        assertEquals(foggyForecast, Forecast.getForecast("Mist"));
        assertEquals(rainyForecast, Forecast.getForecast("Patchy rain possible"));
        assertEquals(snowyForecast, Forecast.getForecast("Patchy snow possible"));
        assertEquals(sleetyForecast, Forecast.getForecast("Patchy sleet possible"));
        assertEquals(rainyForecast, Forecast.getForecast("Patchy freezing drizzle possible"));
        assertEquals(thunderyForecast, Forecast.getForecast("Thundery outbreaks possible"));
        assertEquals(snowyForecast, Forecast.getForecast("Blowing snow"));
        assertEquals(snowyForecast, Forecast.getForecast("Blizzard"));
        assertEquals(foggyForecast, Forecast.getForecast("Fog"));
        assertEquals(foggyForecast, Forecast.getForecast("Freezing fog"));
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
        assertEquals(sleetyForecast, Forecast.getForecast("Light sleet"));
        assertEquals(sleetyForecast, Forecast.getForecast("Moderate or heavy sleet"));
        assertEquals(snowyForecast, Forecast.getForecast("Patchy light snow"));
        assertEquals(snowyForecast, Forecast.getForecast("Light snow"));
        assertEquals(snowyForecast, Forecast.getForecast("Patchy moderate snow"));
        assertEquals(snowyForecast, Forecast.getForecast("Moderate snow"));
        assertEquals(snowyForecast, Forecast.getForecast("Patchy heavy snow"));
        assertEquals(snowyForecast, Forecast.getForecast("Heavy snow"));
        assertEquals(hailForecast, Forecast.getForecast("Ice pellets"));
        assertEquals(rainyForecast, Forecast.getForecast("Light rain shower"));
        assertEquals(rainyForecast, Forecast.getForecast("Moderate or heavy rain shower"));
        assertEquals(rainyForecast, Forecast.getForecast("Torrential rain shower"));
        assertEquals(sleetyForecast, Forecast.getForecast("Light sleet showers"));
        assertEquals(sleetyForecast, Forecast.getForecast("Moderate or heavy sleet showers"));
        assertEquals(snowyForecast, Forecast.getForecast("Light snow showers"));
        assertEquals(snowyForecast, Forecast.getForecast("Moderate or heavy snow showers"));
        assertEquals(hailForecast, Forecast.getForecast("Light showers of ice pellets"));
        assertEquals(hailForecast, Forecast.getForecast("Moderate or heavy showers of ice pellets"));
        assertEquals(rainyForecast, Forecast.getForecast("Patchy light rain with thunder"));
        assertEquals(rainyForecast, Forecast.getForecast("Moderate or heavy rain with thunder"));
        assertEquals(snowyForecast, Forecast.getForecast("Patchy light snow with thunder"));
        assertEquals(snowyForecast, Forecast.getForecast("Moderate or heavy snow with thunder"));
    }
}