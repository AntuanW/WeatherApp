package pl.edu.agh.to2.example.wardrobe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WarmWardrobeTest {

    private static WarmWardrobe warmWardrobe;
    private static Item warmShoes;
    private static Item warmTrousers;
    private static List<Item> warmTop;
    private static List<Item> warmAccessories;

    @BeforeAll
    static void setUp() {
        warmWardrobe = new WarmWardrobe();
        warmShoes = Item.getShoes(Temperature.WARM);
        warmTrousers = Item.getTrousers(Temperature.WARM);
        warmTop = Item.getTop(Temperature.WARM);
        warmAccessories = Item.getAccessories(Temperature.WARM);
    }

    @Test
    void getClothes() {
        Clothes clothes = warmWardrobe.getClothes();
        assertEquals(warmShoes, clothes.getShoes());
        assertEquals(warmTrousers, clothes.getTrousers());
        assertEquals(warmTop, clothes.getTop());
        assertEquals(warmAccessories, clothes.getAccessories());
    }
}