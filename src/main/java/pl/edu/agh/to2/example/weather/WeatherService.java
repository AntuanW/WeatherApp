package pl.edu.agh.to2.example.weather;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.controller.payload.LocationRequest;
import pl.edu.agh.to2.example.exceptions.ResourceNotFoundException;
import pl.edu.agh.to2.example.exceptions.UserNotFoundException;
import pl.edu.agh.to2.example.persistance.ClothesRepository;
import pl.edu.agh.to2.example.persistance.UserConfiguration;
import pl.edu.agh.to2.example.persistance.UserConfigurationRepository;
import pl.edu.agh.to2.example.wardrobe.*;
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;
import java.util.logging.Logger;

@Service
public class WeatherService {
    @Autowired
    private UserConfigurationRepository userConfigurationRepository;
    @Autowired
    private WeatherApiService weatherApiService;
    @Autowired
    private ClothesRepository clothesRepository;

    public Wardrobe getRightWardrobe(String userId) {
        UserConfiguration userConfiguration = userConfigurationRepository.findByUserId(userId).orElseThrow(()-> new UserNotFoundException(userId));
        JsonNode data = weatherApiService.getWeatherData(userConfiguration.getLocation());
        Weather weather = extractWeather(data);
        return pickWardrobe(weather);
    }

    public Weather getWeather(String userId) {
        UserConfiguration userConfiguration = userConfigurationRepository.findByUserId(userId).orElseThrow(()-> new UserNotFoundException(userId));
        JsonNode data = weatherApiService.getWeatherData(userConfiguration.getLocation());
        Weather weather = extractWeather(data);
        return weather;
    }

    private Weather extractWeather(JsonNode data) {
        double temp = data.get("temp_c").asDouble();
        Weather weather = new Weather();
        weather.setTemperature(Temperature.getTemperature(temp));
        weather.setTemperatureCelsius(temp);

        String forecast = data.get("condition").get("text").asText();
        weather.setForecast(Forecast.getForecast(forecast));

        double airCondition = data.get("air_quality").get("pm2_5").asDouble();
        weather.setAirCondition(AirCondition.fromPM25(airCondition));

        return weather;
    }

    private Wardrobe pickWardrobe(Weather weather) {
        Wardrobe wardrobe = new Wardrobe();
        wardrobe.setClothes(clothesRepository.getByTemperature(weather.getTemperature())
                .orElseThrow(() -> new ResourceNotFoundException("Clothes for temperature not found")));
        wardrobe.setUmbrella(weather.getForecast());
        wardrobe.setGasMask(weather.getAirCondition());

        return wardrobe;
    }
}
