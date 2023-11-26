package pl.edu.agh.to2.example.wardrobe;

import pl.edu.agh.to2.example.weather.measures.Temperature;


public class ColdWardrobe extends AbstractWardrobe {
    public ColdWardrobe() {
        clothes = new Clothes();
        clothes.setShoes(Item.getShoes(Temperature.COLD));
        clothes.setTrousers(Item.getTrousers(Temperature.COLD));
        clothes.setTop(Item.getTop(Temperature.COLD));
        clothes.setAccessories(Item.getAccessories(Temperature.COLD));
    }
}
