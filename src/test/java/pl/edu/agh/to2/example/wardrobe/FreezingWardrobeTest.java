package pl.edu.agh.to2.example.wardrobe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FreezingWardrobeTest {
    private static FreezingWardrobe freezingWardrobe;
    private static Clothes freezingClothes;
    private static Item freezingShoes;
    private static Item freezingTrousers;
    private static List<Item> freezingTop;
    private static List<Item> freezingAccessories;

    @BeforeAll
    static void setUp() {
        freezingWardrobe = new FreezingWardrobe();
        freezingClothes = freezingWardrobe.getClothes();
        freezingShoes = Item.getShoes(Temperature.FREEZING);
        freezingTrousers = Item.getTrousers(Temperature.FREEZING);
        freezingTop = Item.getTop(Temperature.FREEZING);
        freezingAccessories = Item.getAccessories(Temperature.FREEZING);
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