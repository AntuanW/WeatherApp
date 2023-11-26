package pl.edu.agh.to2.example.wardrobe;

import pl.edu.agh.to2.example.weather.measures.Temperature;


public class HotWardrobe extends AbstractWardrobe {
    public HotWardrobe() {
        clothes = new Clothes();
        clothes.setShoes(Item.getShoes(Temperature.HOT));
        clothes.setTrousers(Item.getTrousers(Temperature.HOT));
        clothes.setTop(Item.getTop(Temperature.HOT));
        clothes.setAccessories(Item.getAccessories(Temperature.HOT));
    }

}
