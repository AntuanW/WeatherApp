package pl.edu.agh.to2.example.controller;

import org.springframework.http.HttpStatus;
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
        try {
            Wardrobe wardrobe = weatherService.getRightWardrobe(userToken);
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
                    weather.getTemperatureCelsius(),
                    weather.getAirCondition(),
                    weather.getForecast(),
                    weather.getTemperature()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
