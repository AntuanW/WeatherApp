package pl.edu.agh.to2.example.wardrobe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FreezingWardrobeTest {
    private static FreezingWardrobe freezingWardrobe;
    private static Clothes freezingClothes;
    private static String freezingShoes;
    private static String freezingTrousers;
    private static List<String> freezingTop;
    private static List<String> freezingAccessories;

    @BeforeAll
    static void setUp() {
        freezingWardrobe = new FreezingWardrobe();
        freezingClothes = freezingWardrobe.getClothes();
        freezingShoes = String.getShoes(Temperature.FREEZING);
        freezingTrousers = String.getTrousers(Temperature.FREEZING);
        freezingTop = String.getTop(Temperature.FREEZING);
        freezingAccessories = String.getAccessories(Temperature.FREEZING);
    }

    @Test
    void getClothes() {
        Clothes clothes = freezingWardrobe.getClothes();
        assertEquals(freezingClothes, clothes);
        assertEquals(freezingShoes, clothes.getShoes());
        assertEquals(freezingTrousers, clothes.getTrousers());
        assertEquals(freezingTop, clothes.getTop());
        assertEquals(freezingAccessories, clothes.getAccessories());
    }
}