package pl.edu.agh.to2.example.weather;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.utils.LocationRequest;
import pl.edu.agh.to2.example.wardrobe.Wardrobe;
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

@Service
public class WeatherService {

    private final WeatherApiService weatherApiService;
    private Weather weather;

    public WeatherService() {
        weatherApiService = new WeatherApiService();
        weather = new Weather();
    }

    public void setWeatherData(LocationRequest locationRequest) {
        weatherApiService.setWeatherApiURL("");
        try {
            weatherApiService.setWeatherData();
        } catch (Exception e) {
            System.out.println("Something went wrong with getting weather data.");
        }

        double temp = weatherApiService.getTemperature().asDouble();
        weather.setTemperature(Temperature.getTemperature(temp));

        String forecast = weatherApiService.getForecast().asText();
        weather.setForecast(Forecast.getForecast(forecast));

        double airCondition = weatherApiService.getAirQuality().asDouble();
        weather.setAirCondition(AirCondition.fromPM2_5(airCondition));
    }

    public Wardrobe getWardrobe(){
        return weather.getTemperature().getWardrobe();
    }

    public Temperature getTemperature() {
        return weather.getTemperature();
    }

    public Forecast getForecast() {
        return weather.getForecast();
    }

    public AirCondition getAirCondition() {
        return weather.getAirCondition();
    }
}
