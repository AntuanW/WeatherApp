package pl.edu.agh.to2.example.wardrobe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WarmWardrobeTest {

    private static WarmWardrobe warmWardrobe;
    private static String warmShoes;
    private static String warmTrousers;
    private static List<String> warmTop;
    private static List<String> warmAccessories;

    @BeforeAll
    static void setUp() {
        warmWardrobe = new WarmWardrobe();
        warmShoes = String.getShoes(Temperature.WARM);
        warmTrousers = String.getTrousers(Temperature.WARM);
        warmTop = String.getTop(Temperature.WARM);
        warmAccessories = String.getAccessories(Temperature.WARM);
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