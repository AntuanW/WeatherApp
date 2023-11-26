package pl.edu.agh.to2.example.wardrobe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private static List<Item> freezingAccessories;
    private static List<Item> coldAccessories;
    private static List<Item> hotAccessories;
    private static List<Item> warmAccessories;

    @BeforeAll
    static void setUp() {
        freezingAccessories = List.of(Item.SCARF, Item.GLOVES, Item.WINTER_CAP);
        coldAccessories = List.of(Item.SCARF);
        hotAccessories = List.of(Item.SUNGLASSES, Item.HAT, Item.BASEBALL_CAP);
        warmAccessories = List.of(Item.HAT, Item.BASEBALL_CAP);
    }

    @Test
    void getAccessories() {
        assertEquals(freezingAccessories, Item.getAccessories(Temperature.FREEZING));
        assertEquals(coldAccessories, Item.getAccessories(Temperature.COLD));
        assertEquals(hotAccessories, Item.getAccessories(Temperature.HOT));
        assertEquals(warmAccessories, Item.getAccessories(Temperature.WARM));
    }

    @Test
    void getTrousers() {
        assertEquals(Item.TROUSERS, Item.getTrousers(Temperature.FREEZING));
        assertEquals(Item.TROUSERS, Item.getTrousers(Temperature.COLD));
        assertEquals(Item.SHORTS, Item.getTrousers(Temperature.HOT));
        assertEquals(Item.TROUSERS, Item.getTrousers(Temperature.WARM));
    }

    @Test
    void getTop() {
        assertEquals(List.of(Item.JUMPER, Item.CARDIGAN, Item.JACKET), Item.getTop(Temperature.FREEZING));
        assertEquals(List.of(Item.JUMPER, Item.LIGHT_JACKET), Item.getTop(Temperature.COLD));
        assertEquals(List.of(Item.T_SHIRT, Item.CARDIGAN), Item.getTop(Temperature.WARM));
        assertEquals(List.of(Item.T_SHIRT), Item.getTop(Temperature.HOT));
    }

    @Test
    void getShoes() {
        assertEquals(Item.BOOTS, Item.getShoes(Temperature.FREEZING));
        assertEquals(Item.BOOTS, Item.getShoes(Temperature.COLD));
        assertEquals(Item.SNEAKERS, Item.getShoes(Temperature.WARM));
        assertEquals(Item.SANDALS, Item.getShoes(Temperature.HOT));
    }
}