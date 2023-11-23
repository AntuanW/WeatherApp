package pl.edu.agh.to2.example.weather.measures;

import pl.edu.agh.to2.example.wardrobe.*;

public enum Temperature {
    FREEZING,
    COLD,
    WARM,
    HOT;

    public Wardrobe getWardrobe(){
        return switch (this){
            case FREEZING -> new FreezingWardrobe();
            case COLD -> new ColdWardrobe();
            case WARM -> new WarmWardrobe();
            case HOT -> new HotWardrobe();
        };
    }
}
