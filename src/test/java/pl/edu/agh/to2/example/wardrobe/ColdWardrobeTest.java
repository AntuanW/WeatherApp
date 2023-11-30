package pl.edu.agh.to2.example.wardrobe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColdWardrobeTest {
    private static ColdWardrobe coldWardrobe;
    private static String coldShoes;
    private static String coldTrousers;
    private static List<String> coldTop;
    private static List<String> coldAccessories;

    @BeforeAll
    static void setUp() {
        coldWardrobe = new ColdWardrobe();
        coldShoes = String.getShoes(Temperature.COLD);
        coldTrousers = String.getTrousers(Temperature.COLD);
        coldTop = String.getTop(Temperature.COLD);
        coldAccessories = String.getAccessories(Temperature.COLD);
    }

    @Test
    void getClothes() {
        Clothes clothes = coldWardrobe.getClothes();
        assertEquals(coldShoes, clothes.getShoes());
        assertEquals(coldTrousers, clothes.getTrousers());
        assertEquals(coldTop, clothes.getTop());
        assertEquals(coldAccessories, clothes.getAccessories());
    }
}