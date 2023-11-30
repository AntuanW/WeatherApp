package pl.edu.agh.to2.example.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.to2.example.controller.payload.WeatherResponse;
import pl.edu.agh.to2.example.wardrobe.Wardrobe;
import pl.edu.agh.to2.example.weather.Weather;
import pl.edu.agh.to2.example.weather.WeatherService;

@RestController
@CrossOrigin()
@RequestMapping(path = "/weatherapp")
public class WeatherController {
    private final WeatherService weatherService;
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    //tu request z podanym userId z frontu
    @GetMapping("/wardrobe")
    public Wardrobe getWardrobe() {
        return weatherService.getRightWardrobe("aa");
    }

    @GetMapping("/weather")
    public WeatherResponse getWeather() {
        Weather weather = weatherService.getWeather("aa");
        return new WeatherResponse(
                weather.getTemperatureCelsius(),
                weather.getAirCondition(),
                weather.getForecast(),
                weather.getTemperature()
        );
    }
}
