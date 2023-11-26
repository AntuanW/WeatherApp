package pl.edu.agh.to2.example.wardrobe;

import pl.edu.agh.to2.example.weather.measures.Temperature;


public class ColdWardrobe extends AbstractWardrobe {
    public ColdWardrobe() {
        clothes = new Clothes.Builder()
                .setShoes(Item.getShoes(Temperature.COLD))
                .setTrousers(Item.getTrousers(Temperature.COLD))
                .setTop(Item.getTop(Temperature.COLD))
                .setAccessories(Item.getAccessories(Temperature.COLD))
                .build();
    }
}
