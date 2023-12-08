package pl.edu.agh.to2.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.exceptions.ResourceNotFoundException;
import pl.edu.agh.to2.example.exceptions.UserNotFoundException;
import pl.edu.agh.to2.example.model.Location;
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
    private TemperatureService temperatureService;
    private ClothesRepository clothesRepository;

    @Autowired
    public WeatherService(UserConfigurationRepository userConfigurationRepository, WeatherApiService weatherApiService,
                          TemperatureService temperatureService, ClothesRepository clothesRepository) {
        this.userConfigurationRepository = userConfigurationRepository;
        this.weatherApiService = weatherApiService;
        this.temperatureService = temperatureService;
        this.clothesRepository = clothesRepository;
    }

    public Wardrobe getRightWardrobe(String userId) {
        Weather weather = getWeather(userId);
        return pickWardrobe(weather);
    }

    public Weather getWeather(String userId) {
        UserConfiguration userConfiguration = userConfigurationRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Location locationProvided = userConfiguration.getLocation().orElseThrow(() -> new ResourceNotFoundException("No location provided"));
        if (locationProvided.latitude2().isPresent() && locationProvided.longitude2().isPresent()) {
            Weather weather1 = extractWeather(weatherApiService.getWeatherData(locationProvided.latitude(), locationProvided.longitude()));
            Weather weather2 = extractWeather(weatherApiService.getWeatherData(locationProvided.latitude2().get(), locationProvided.longitude2().get()));
            return combineWeather(weather1, weather2);
        }else {
            JsonNode data = weatherApiService.getWeatherData(locationProvided.latitude(), locationProvided.longitude());
            return extractWeather(data);
        }
    }

    private Weather combineWeather(Weather weather1, Weather weather2) {
        Weather weather = new Weather();
        // location name
        weather.setLocationName(weather1.getLocationName() + " and " + weather2.getLocationName());
        // temperature
        weather.setTemperatureCelsius(Math.min(weather1.getTemperatureCelsius(), weather2.getTemperatureCelsius()));
        weather.setTemperature(
                weather1.getTemperatureCelsius() < weather2.getTemperatureCelsius()
                        ? weather1.getTemperature()
                        : weather2.getTemperature()
        );
        // forecast
        weather.setForecast(
                weather1.getForecast().ordinal() > weather2.getForecast().ordinal()
                        ? weather1.getForecast()
                        : weather2.getForecast()
        );
        // air condition
        weather.setAirCondition(
                weather1.getAirCondition().ordinal() > weather2.getAirCondition().ordinal()
                        ? weather1.getAirCondition()
                        : weather2.getAirCondition()
        );
        return weather;
    }

    private Weather extractWeather(JsonNode data) {
        Weather weather = new Weather();
        JsonNode currentData = data.get("current");

        String locationName = data.get("location").get("name").asText();
        weather.setLocationName(locationName);

        String forecast = currentData.get("condition").get("text").asText();
        weather.setForecast(Forecast.getForecast(forecast));

        double airCondition = currentData.get("air_quality").get("pm2_5").asDouble();
        weather.setAirCondition(AirCondition.fromPM25(airCondition));

        double temperature = currentData.get("temp_c").asDouble();
        weather.setTemperatureCelsius(temperature);

        double windSpeedKmPerHour = currentData.get("wind_kph").asDouble();
        weather.setTemperature(Temperature.getTemperature(temperatureService.calculateSensedTemperature(temperature, windSpeedKmPerHour)));

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
