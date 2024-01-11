package pl.edu.agh.to2.example.persistance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.to2.example.wardrobe.Clothes;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClothesRepositoryTest {

    @InjectMocks
    private ClothesRepository clothesRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetByTemperatureHot() {
        Temperature temperature = Temperature.HOT;
        Optional<Clothes> result = clothesRepository.getByTemperature(temperature);

        assertTrue(result.isPresent());

        Clothes clothes = result.get();
        assertEquals("Sandals", clothes.getShoes());
        assertEquals("Shorts", clothes.getTrousers());
        assertEquals(List.of("T-shirt"), clothes.getTop());
        assertEquals(List.of("Sunglasses", "Hat", "Baseball cap"), clothes.getAccessories());
    }

    @Test
    void testGetByTemperatureWarm() {
        Temperature temperature = Temperature.WARM;
        Optional<Clothes> result = clothesRepository.getByTemperature(temperature);

        assertTrue(result.isPresent());

        Clothes clothes = result.get();
        assertEquals("Sneakers", clothes.getShoes());
        assertEquals("Trousers", clothes.getTrousers());
        assertEquals(List.of("T-shirt", "Cardigan"), clothes.getTop());
        assertEquals(List.of("Hat", "Baseball cap"), clothes.getAccessories());
    }

    @Test
    void testGetByTemperatureCold() {
        Temperature temperature = Temperature.COLD;
        Optional<Clothes> result = clothesRepository.getByTemperature(temperature);
        assertTrue(result.isPresent());

        Clothes clothes = result.get();
        assertEquals("Boots", clothes.getShoes());
        assertEquals("Trousers", clothes.getTrousers());
        assertEquals(List.of("Jumper", "Light jacket"), clothes.getTop());
        assertEquals(List.of("Scarf"), clothes.getAccessories());
    }

    @Test
    void testGetByTemperatureFreezing() {
        Temperature temperature = Temperature.FREEZING;
        Optional<Clothes> result = clothesRepository.getByTemperature(temperature);
        assertTrue(result.isPresent());

        Clothes clothes = result.get();
        assertEquals("Boots", clothes.getShoes());
        assertEquals("Trousers", clothes.getTrousers());
        assertEquals(List.of("Jumper", "Cardigan", "Jacket"), clothes.getTop());
        assertEquals(List.of("Scarf", "Gloves", "Winter cap"), clothes.getAccessories());
    }
}