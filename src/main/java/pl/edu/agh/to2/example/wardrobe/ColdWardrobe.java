package pl.edu.agh.to2.example.wardrobe;

import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;

public class ColdWardrobe extends AbstractWardrobe {
    public ColdWardrobe() {
        clothes = new Clothes();
        clothes.setShoes(Item.getShoes(Temperature.COLD));
        clothes.setTrousers(Item.getTrousers(Temperature.COLD));
        clothes.setTop(Item.getTop(Temperature.COLD));
        clothes.setAccessories(Item.getAccessories(Temperature.COLD));
    }
}
