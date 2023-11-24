package pl.edu.agh.to2.example.weather;

import pl.edu.agh.to2.example.wardrobe.Wardrobe;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

public class WeatherService {

    public Wardrobe getWardrobe(){
        return null;
    }

    public Temperature getTemperature() {
        return Temperature.WARM;
    }

    public Forecast getForecast() {
        return Forecast.CLEAR;
    }
}
