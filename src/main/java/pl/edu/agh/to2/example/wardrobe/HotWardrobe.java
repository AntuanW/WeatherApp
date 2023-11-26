package pl.edu.agh.to2.example.wardrobe;

import pl.edu.agh.to2.example.weather.measures.Temperature;


public class HotWardrobe extends AbstractWardrobe {
    public HotWardrobe() {
        clothes = new Clothes.Builder()
                .setShoes(Item.getShoes(Temperature.HOT))
                .setTrousers(Item.getTrousers(Temperature.HOT))
                .setTop(Item.getTop(Temperature.HOT))
                .setAccessories(Item.getAccessories(Temperature.HOT))
                .build();
    }

}
