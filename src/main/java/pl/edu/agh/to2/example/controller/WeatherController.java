package pl.edu.agh.to2.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.to2.example.controller.payload.WeatherResponse;
import pl.edu.agh.to2.example.service.WardrobeService;
import pl.edu.agh.to2.example.service.WeatherService;
import pl.edu.agh.to2.example.wardrobe.Wardrobe;
import pl.edu.agh.to2.example.weather.Weather;

@RestController
@CrossOrigin()
@RequestMapping(path = "/weatherapp")
public class WeatherController {
    private final WeatherService weatherService;
    private final WardrobeService wardrobeService;

    public WeatherController(WeatherService weatherService, WardrobeService wardrobeService) {
        this.weatherService = weatherService;
        this.wardrobeService = wardrobeService;
    }

    @GetMapping("/wardrobe")
    public ResponseEntity<Wardrobe> getWardrobe(
            @RequestHeader("Authorization") String userToken
    ) {
        try {
            Wardrobe wardrobe = wardrobeService.getRightWardrobe(userToken);
            return ResponseEntity.ok().body(wardrobe);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse> getWeather(
            @RequestHeader("Authorization") String userToken
    ) {
        try {
            Weather weather = weatherService.getWeather(userToken);
            return ResponseEntity.ok().body(new WeatherResponse(
                    weather.getLocationName(),
                    weather.getTemperatureCelsius(),
                    weather.getAirCondition(),
                    weather.getForecast(),
                    weather.getTemperature()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
