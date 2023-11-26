package pl.edu.agh.to2.example.wardrobe;

import pl.edu.agh.to2.example.weather.measures.Temperature;


public class WarmWardrobe extends AbstractWardrobe {
    public WarmWardrobe() {
        clothes = new Clothes.Builder()
                .setShoes(Item.getShoes(Temperature.WARM))
                .setTrousers(Item.getTrousers(Temperature.WARM))
                .setTop(Item.getTop(Temperature.WARM))
                .setAccessories(Item.getAccessories(Temperature.WARM))
                .build();
    }

}
