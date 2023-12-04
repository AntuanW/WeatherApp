package pl.edu.agh.to2.example.wardrobe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class WardrobeTest {

    private Wardrobe wardrobe;
    private Clothes clothes;
    private Forecast rainForecast;
    private Forecast clearForecast;
    private AirCondition maskAirCondition;
    private AirCondition healthyAirCondition;

    @BeforeEach
    void setUp() {
        clothes = mock(Clothes.class);
        rainForecast = Forecast.RAINY;
        clearForecast = Forecast.CLEAR;
        maskAirCondition = AirCondition.UNHEALTHY;
        healthyAirCondition = AirCondition.GOOD;
        wardrobe = new Wardrobe();
    }

    @Test
    void testGetClothes() {
        wardrobe.setClothes(clothes);
        assertEquals(clothes, wardrobe.getClothes());
    }

    @Test
    void testGetIfUmbrella() {
        wardrobe.setUmbrella(rainForecast);
        assertTrue(wardrobe.getIfUmbrella());
        wardrobe.setUmbrella(clearForecast);
        assertFalse(wardrobe.getIfUmbrella());
    }

    @Test
    void testGetIfGasMask() {
        wardrobe.setGasMask(maskAirCondition);
        assertTrue(wardrobe.getIfGasMask());
        wardrobe.setGasMask(healthyAirCondition);
        assertFalse(wardrobe.getIfGasMask());
    }
}