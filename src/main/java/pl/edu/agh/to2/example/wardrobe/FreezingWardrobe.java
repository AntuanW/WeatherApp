package pl.edu.agh.to2.example.wardrobe;

import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;

public class FreezingWardrobe extends AbstractWardrobe{
        public FreezingWardrobe() {
                clothes = new Clothes();
                clothes.setShoes(Item.getShoes(Temperature.FREEZING));
                clothes.setTrousers(Item.getTrousers(Temperature.FREEZING));
                clothes.setTop(Item.getTop(Temperature.FREEZING));
                clothes.setAccessories(Item.getAccessories(Temperature.FREEZING));
        }

}
