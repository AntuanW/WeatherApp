package pl.edu.agh.to2.example.wardrobe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotWardrobeTest {

    private static HotWardrobe hotWardrobe;
    private static Clothes hotClothes;
    private static Item hotShoes;
    private static Item hotTrousers;
    private static List<Item> hotTop;
    private static List<Item> hotAccessories;

    @BeforeAll
    static void setUp() {
        hotWardrobe = new HotWardrobe();
        hotShoes = Item.getShoes(Temperature.HOT);
        hotTrousers = Item.getTrousers(Temperature.HOT);
        hotTop = Item.getTop(Temperature.HOT);
        hotAccessories = Item.getAccessories(Temperature.HOT);
    }

    @Test
    void getClothes() {
        Clothes clothes = hotWardrobe.getClothes();
        assertEquals(hotShoes, clothes.getShoes());
        assertEquals(hotTrousers, clothes.getTrousers());
        assertEquals(hotTop, clothes.getTop());
        assertEquals(hotAccessories, clothes.getAccessories());
    }
}