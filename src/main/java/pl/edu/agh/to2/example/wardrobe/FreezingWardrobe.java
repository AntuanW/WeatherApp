package pl.edu.agh.to2.example.wardrobe;

import pl.edu.agh.to2.example.weather.measures.Temperature;


public class FreezingWardrobe extends AbstractWardrobe {
    public FreezingWardrobe() {
        clothes = new Clothes.Builder()
                .setShoes(Item.getShoes(Temperature.FREEZING))
                .setTrousers(Item.getTrousers(Temperature.FREEZING))
                .setTop(Item.getTop(Temperature.FREEZING))
                .setAccessories(Item.getAccessories(Temperature.FREEZING))
                .build();
    }

}
