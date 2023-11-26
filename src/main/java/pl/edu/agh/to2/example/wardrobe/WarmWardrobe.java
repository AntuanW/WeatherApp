package pl.edu.agh.to2.example.wardrobe;

import pl.edu.agh.to2.example.weather.measures.Temperature;


public class WarmWardrobe extends AbstractWardrobe {
    public WarmWardrobe() {
        clothes = new Clothes();
        clothes.setShoes(Item.getShoes(Temperature.WARM));
        clothes.setTrousers(Item.getTrousers(Temperature.WARM));
        clothes.setTop(Item.getTop(Temperature.WARM));
        clothes.setAccessories(Item.getAccessories(Temperature.WARM));
    }

}
