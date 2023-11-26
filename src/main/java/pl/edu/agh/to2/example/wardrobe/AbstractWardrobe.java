package pl.edu.agh.to2.example.wardrobe;

import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;


public abstract class AbstractWardrobe implements Wardrobe{
    protected boolean takeUmbrella;
    protected boolean takeGasMask;
    protected Clothes clothes;

    @Override
    public boolean checkUmbrella(Forecast forecast) {
        return forecast.checkIfUmbrella();
    }

    @Override
    public boolean checkGasMask(AirCondition airCondition) {
        return airCondition.checkIfGasMask();
    }

    @Override
    public Clothes getClothes() {
        return clothes;
    }

    @Override
    public void setUmbrella(boolean takeUmbrella) {
        this.takeUmbrella = takeUmbrella;
    }

    @Override
    public void setGasMask(boolean takeGasMask) {
        this.takeGasMask = takeGasMask;
    }

    @Override
    public boolean getUmbrella() {
        return takeUmbrella;
    }

    @Override
    public boolean getGasMask() {
        return takeGasMask;
    }
}
