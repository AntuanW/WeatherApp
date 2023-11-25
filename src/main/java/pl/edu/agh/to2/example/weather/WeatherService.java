package pl.edu.agh.to2.example.weather;

import pl.edu.agh.to2.example.wardrobe.Wardrobe;
<<<<<<< HEAD
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

public class WeatherService {

    private final WeatherApiService weatherApiService;
    private Weather weather;

    public WeatherService() {
        weatherApiService = new WeatherApiService();
    }

    public Wardrobe getWardrobe(){
        return null;
    }

    public Temperature getTemperature() {
        return Temperature.WARM;
    }

    public Forecast getForecast() {
        return Forecast.CLEAR;
    }
=======

public class WeatherService {

    public Wardrobe getWardrobe(){
        return null;
    }
>>>>>>> 88c72fde0e9388a554e0796b9df90cd7a21a36f9
}
