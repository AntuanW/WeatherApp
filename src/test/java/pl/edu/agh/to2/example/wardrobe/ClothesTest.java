package pl.edu.agh.to2.example.wardrobe;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClothesTest {

    @Test
    void testClothesBuilder() {
        Item shoes = Item.BOOTS;
        Item trousers = Item.TROUSERS;
        List<Item> top = List.of(Item.T_SHIRT, Item.JACKET);
        List<Item> accessories = List.of(Item.SCARF, Item.GLOVES);

        Clothes clothes = new Clothes.Builder()
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
