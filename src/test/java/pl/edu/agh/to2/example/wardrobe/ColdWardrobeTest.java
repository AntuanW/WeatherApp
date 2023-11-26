package pl.edu.agh.to2.example.wardrobe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColdWardrobeTest {
    private static ColdWardrobe coldWardrobe;
    private static Item coldShoes;
    private static Item coldTrousers;
    private static List<Item> coldTop;
    private static List<Item> coldAccessories;

    @BeforeAll
    static void setUp() {
        coldWardrobe = new ColdWardrobe();
        coldShoes = Item.getShoes(Temperature.COLD);
        coldTrousers = Item.getTrousers(Temperature.COLD);
        coldTop = Item.getTop(Temperature.COLD);
        coldAccessories = Item.getAccessories(Temperature.COLD);
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