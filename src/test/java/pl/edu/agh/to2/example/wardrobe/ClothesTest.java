package pl.edu.agh.to2.example.wardrobe;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClothesTest {

    @Test
    void testClothesBuilder() {
        String shoes = String.BOOTS;
        String trousers = String.TROUSERS;
        List<String> top = List.of(String.T_SHIRT, String.JACKET);
        List<String> accessories = List.of(String.SCARF, String.GLOVES);

        Clothes clothes = new ClothesBuilder()
                .setShoes(shoes)
                .setTrousers(trousers)
                .setTop(top)
                .setAccessories(accessories)
                .build();

        assertNotNull(clothes);
        assertEquals(shoes, clothes.getShoes());
        assertEquals(trousers, clothes.getTrousers());
        assertEquals(top, clothes.getTop());
        assertEquals(accessories, clothes.getAccessories());
    }
}
