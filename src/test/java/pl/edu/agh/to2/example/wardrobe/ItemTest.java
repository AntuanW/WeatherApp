package pl.edu.agh.to2.example.wardrobe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private static List<String> freezingAccessories;
    private static List<String> coldAccessories;
    private static List<String> hotAccessories;
    private static List<String> warmAccessories;

    @BeforeAll
    static void setUp() {
        freezingAccessories = List.of(String.SCARF, String.GLOVES, String.WINTER_CAP);
        coldAccessories = List.of(String.SCARF);
        hotAccessories = List.of(String.SUNGLASSES, String.HAT, String.BASEBALL_CAP);
        warmAccessories = List.of(String.HAT, String.BASEBALL_CAP);
    }

    @Test
    void getAccessories() {
        assertEquals(freezingAccessories, String.getAccessories(Temperature.FREEZING));
        assertEquals(coldAccessories, String.getAccessories(Temperature.COLD));
        assertEquals(hotAccessories, String.getAccessories(Temperature.HOT));
        assertEquals(warmAccessories, String.getAccessories(Temperature.WARM));
    }

    @Test
    void getTrousers() {
        assertEquals(String.TROUSERS, String.getTrousers(Temperature.FREEZING));
        assertEquals(String.TROUSERS, String.getTrousers(Temperature.COLD));
        assertEquals(String.SHORTS, String.getTrousers(Temperature.HOT));
        assertEquals(String.TROUSERS, String.getTrousers(Temperature.WARM));
    }

    @Test
    void getTop() {
        assertEquals(List.of(String.JUMPER, String.CARDIGAN, String.JACKET), String.getTop(Temperature.FREEZING));
        assertEquals(List.of(String.JUMPER, String.LIGHT_JACKET), String.getTop(Temperature.COLD));
        assertEquals(List.of(String.T_SHIRT, String.CARDIGAN), String.getTop(Temperature.WARM));
        assertEquals(List.of(String.T_SHIRT), String.getTop(Temperature.HOT));
    }

    @Test
    void getShoes() {
        assertEquals(String.BOOTS, String.getShoes(Temperature.FREEZING));
        assertEquals(String.BOOTS, String.getShoes(Temperature.COLD));
        assertEquals(String.SNEAKERS, String.getShoes(Temperature.WARM));
        assertEquals(String.SANDALS, String.getShoes(Temperature.HOT));
    }
}