package pl.edu.agh.to2.example.wardrobe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClothesTest {

    private static Clothes clothes;
    private static String shoes;
    private static String trousers;
    private static List<String> top;
    private static List<String> accessories;

    @BeforeAll
    static void setUp() {
        shoes = "shoes";
        trousers = "trousers";
        top = List.of("top1", "top2");
        accessories = List.of("accessory1", "accessory2");
        clothes = new Clothes(shoes, trousers, top, accessories);
    }

    @Test
    void getShoes() {
        assertEquals(shoes, clothes.getShoes());
    }

    @Test
    void getTrousers() {
        assertEquals(trousers, clothes.getTrousers());
    }

    @Test
    void getTop() {
        assertEquals(top, clothes.getTop());
    }

    @Test
    void getAccessories() {
        assertEquals(accessories, clothes.getAccessories());
    }
}