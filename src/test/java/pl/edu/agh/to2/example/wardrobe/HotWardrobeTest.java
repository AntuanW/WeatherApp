package pl.edu.agh.to2.example.wardrobe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotWardrobeTest {

    private static HotWardrobe hotWardrobe;
    private static Clothes hotClothes;
    private static String hotShoes;
    private static String hotTrousers;
    private static List<String> hotTop;
    private static List<String> hotAccessories;

    @BeforeAll
    static void setUp() {
        hotWardrobe = new HotWardrobe();
        hotClothes = hotWardrobe.getClothes();
        hotShoes = String.getShoes(Temperature.HOT);
        hotTrousers = String.getTrousers(Temperature.HOT);
        hotTop = String.getTop(Temperature.HOT);
        hotAccessories = String.getAccessories(Temperature.HOT);
    }

    @Test
    void getClothes() {
        Clothes clothes = hotWardrobe.getClothes();
        assertEquals(hotClothes, clothes);
        assertEquals(hotShoes, clothes.getShoes());
        assertEquals(hotTrousers, clothes.getTrousers());
        assertEquals(hotTop, clothes.getTop());
        assertEquals(hotAccessories, clothes.getAccessories());
    }
}