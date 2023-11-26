package pl.edu.agh.to2.example.wardrobe;

import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;


public interface Wardrobe {
    boolean checkUmbrella(Forecast forecast);
    boolean checkGasMask(AirCondition airCondition);
    Clothes getClothes();
    void setUmbrella(boolean takeUmbrella);
    void setGasMask(boolean takeGasMask);
    boolean getUmbrella();
    boolean getGasMask();
}
