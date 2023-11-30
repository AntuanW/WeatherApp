package pl.edu.agh.to2.example.weather;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.to2.example.utils.LocationRequest;
import pl.edu.agh.to2.example.utils.WeatherRequest;
import pl.edu.agh.to2.example.wardrobe.Wardrobe;
import java.util.logging.Logger;


import javax.persistence.EntityNotFoundException;

@RestController
@CrossOrigin()
@RequestMapping(path = "/weatherapp")
public class WeatherController {
    private final WeatherService weatherService;

    private final Logger logger = Logger.getLogger(getClass().getName());

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/wardrobe")
    public Wardrobe getWardrobe() {
        return weatherService.getRightWardrobe();
    }

    @GetMapping("/weather")
    public WeatherRequest getWeather() {
        return new WeatherRequest(
                weatherService.getTempCelsius(),
                weatherService.getAirCondition(),
                weatherService.getForecast(),
                weatherService.getTemperature()
        );
    }

    @PostMapping("/location")
    public ResponseEntity<String> postLocation(
            @RequestBody LocationRequest locationRequest
    ) {
        try {
            logger.info(() -> "Message: " + locationRequest.toString());
            weatherService.setWeatherData(locationRequest);
            return ResponseEntity.ok().body("Weather data set successfully.");
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong with getting weather data.");
        }

    }
}
