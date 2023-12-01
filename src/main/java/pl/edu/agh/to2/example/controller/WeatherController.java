package pl.edu.agh.to2.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.to2.example.controller.payload.WeatherResponse;
import pl.edu.agh.to2.example.wardrobe.Wardrobe;
import pl.edu.agh.to2.example.weather.Weather;
import pl.edu.agh.to2.example.service.WeatherService;

@RestController
@CrossOrigin()
@RequestMapping(path = "/weatherapp")
public class WeatherController {
    private final WeatherService weatherService;
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/wardrobe")
    public ResponseEntity<Wardrobe> getWardrobe(
            @RequestHeader("Authorization") String userToken
    ) {
        Wardrobe wardrobe = weatherService.getRightWardrobe(userToken);
        return ResponseEntity.ok().body(wardrobe);
    }

    @GetMapping("/weather")
    public WeatherResponse getWeather(
            @RequestHeader("Authorization") String userToken
    ) {
        Weather weather = weatherService.getWeather(userToken);
        return new WeatherResponse(
                weather.getTemperatureCelsius(),
                weather.getAirCondition(),
                weather.getForecast(),
                weather.getTemperature()
        );
    }
}
