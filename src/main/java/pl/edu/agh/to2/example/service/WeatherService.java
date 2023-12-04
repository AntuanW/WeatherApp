package pl.edu.agh.to2.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.exceptions.ResourceNotFoundException;
import pl.edu.agh.to2.example.exceptions.UserNotFoundException;
import pl.edu.agh.to2.example.persistance.ClothesRepository;
import pl.edu.agh.to2.example.persistance.UserConfiguration;
import pl.edu.agh.to2.example.persistance.UserConfigurationRepository;
import pl.edu.agh.to2.example.wardrobe.*;
import pl.edu.agh.to2.example.weather.Weather;
import pl.edu.agh.to2.example.weather.measures.*;

@Service
public class WeatherService {
    private UserConfigurationRepository userConfigurationRepository;
    private WeatherApiService weatherApiService;
    private ClothesRepository clothesRepository;

    @Autowired
    public WeatherService(UserConfigurationRepository userConfigurationRepository, WeatherApiService weatherApiService, ClothesRepository clothesRepository) {
        this.userConfigurationRepository = userConfigurationRepository;
        this.weatherApiService = weatherApiService;
        this.clothesRepository = clothesRepository;
    }

    public Wardrobe getRightWardrobe(String userId) {
        UserConfiguration userConfiguration = userConfigurationRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException(userId));
        JsonNode data = weatherApiService.getWeatherData(userConfiguration.getLocation().orElseThrow(() -> new ResourceNotFoundException("No location provided")));
        Weather weather = extractWeather(data);
        return pickWardrobe(weather);
    }

    public Weather getWeather(String userId) {
        UserConfiguration userConfiguration = userConfigurationRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException(userId));
        JsonNode data = weatherApiService.getWeatherData(userConfiguration.getLocation().orElseThrow(() -> new ResourceNotFoundException("No location provided")));
        return extractWeather(data);
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
