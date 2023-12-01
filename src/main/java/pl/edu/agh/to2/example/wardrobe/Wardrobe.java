package pl.edu.agh.to2.example.wardrobe;

import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;


public class Wardrobe {
    private boolean takeUmbrella;
    private boolean takeGasMask;
    private Clothes clothes;

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public void setUmbrella(Forecast forecast) {
        this.takeUmbrella = (Forecast.RAINY == forecast
                || Forecast.SLEETY == forecast
                || Forecast.SNOWY == forecast
                || Forecast.HAIL == forecast);
    }

    public void setGasMask(AirCondition airCondition) {
        this.takeGasMask = (AirCondition.UNHEALTHY == airCondition
                || AirCondition.VERY_UNHEALTHY == airCondition);
    }

    public boolean getIfUmbrella() {
        return takeUmbrella;
    }

    public boolean getIfGasMask() {
        return takeGasMask;
    }
}
