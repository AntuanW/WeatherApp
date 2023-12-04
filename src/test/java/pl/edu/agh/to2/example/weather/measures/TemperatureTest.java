package pl.edu.agh.to2.example.weather.measures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureTest {

    private Temperature freezingTemperature;
    private Temperature coldTemperature;
    private Temperature warmTemperature;
    private Temperature hotTemperature;

    @BeforeEach
    void setUp() {
        freezingTemperature = Temperature.FREEZING;
        coldTemperature = Temperature.COLD;
        warmTemperature = Temperature.WARM;
        hotTemperature = Temperature.HOT;
    }

    @Test
    void testGetTemperature() {
        assertEquals(freezingTemperature, Temperature.getTemperature(-1.0));
        assertEquals(freezingTemperature, Temperature.getTemperature(0.0));
        assertEquals(coldTemperature, Temperature.getTemperature(3.0));
        assertEquals(coldTemperature, Temperature.getTemperature(14.0));
        assertEquals(warmTemperature, Temperature.getTemperature(15.0));
        assertEquals(warmTemperature, Temperature.getTemperature(22.0));
        assertEquals(hotTemperature, Temperature.getTemperature(23.0));
        assertEquals(hotTemperature, Temperature.getTemperature(30.0));
    }
}