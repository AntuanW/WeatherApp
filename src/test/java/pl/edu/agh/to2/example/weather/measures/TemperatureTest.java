package pl.edu.agh.to2.example.weather.measures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureTest {
    private static Temperature freezingTemp;
    private static Temperature coldTemp;
    private static Temperature warmTemp;
    private static Temperature hotTemp;

    @BeforeEach
    void setUp() {
        freezingTemp = Temperature.FREEZING;
        coldTemp = Temperature.COLD;
        warmTemp = Temperature.WARM;
        hotTemp = Temperature.HOT;
    }

    @Test
    void getWardrobe() {
        assertTrue(freezingTemp.getWardrobe() instanceof FreezingWardrobe);
        assertTrue(coldTemp.getWardrobe() instanceof ColdWardrobe);
        assertTrue(warmTemp.getWardrobe() instanceof WarmWardrobe);
        assertTrue(hotTemp.getWardrobe() instanceof HotWardrobe);
        assertFalse(freezingTemp.getWardrobe() instanceof HotWardrobe);
        assertFalse(coldTemp.getWardrobe() instanceof FreezingWardrobe);
        assertFalse(warmTemp.getWardrobe() instanceof ColdWardrobe);
        assertFalse(hotTemp.getWardrobe() instanceof WarmWardrobe);
    }

    @Test
    void getTemperature() {
        assertEquals(freezingTemp, Temperature.getTemperature(-1.5));
        assertEquals(coldTemp, Temperature.getTemperature(10.1));
        assertEquals(warmTemp, Temperature.getTemperature(16.9));
        assertEquals(hotTemp, Temperature.getTemperature(29.5));
        assertNotEquals(freezingTemp, Temperature.getTemperature(4.5));
        assertNotEquals(coldTemp, Temperature.getTemperature(18.6));
        assertNotEquals(warmTemp, Temperature.getTemperature(24.5));
        assertNotEquals(hotTemp, Temperature.getTemperature(2.0));
    }
}