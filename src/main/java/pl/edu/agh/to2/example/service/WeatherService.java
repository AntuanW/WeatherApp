package pl.edu.agh.to2.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.exceptions.ResourceNotFoundException;
import pl.edu.agh.to2.example.exceptions.UserNotFoundException;
import pl.edu.agh.to2.example.model.Location;
import pl.edu.agh.to2.example.persistance.UserConfiguration;
import pl.edu.agh.to2.example.persistance.UserConfigurationRepository;
import pl.edu.agh.to2.example.weather.Weather;
import pl.edu.agh.to2.example.weather.measures.*;

@Service
public class WeatherService {
    private final UserConfigurationRepository userConfigurationRepository;
    private final WeatherApiService weatherApiService;
    private final TemperatureService temperatureService;
    private static final String CURRENT_PARAM = "current";
    private static final String NAME_PARAM = "name";
    private static final String TEXT_PARAM = "text";
    private static final String LOCATION_PARAM = "location";
    private static final String CONDITION_PARAM = "condition";
    private static final String AIR_QUALITY_PARAM = "air_quality";
    private static final String TEMP_C_PARAM = "temp_c";
    private static final String WIND_KPH_PARAM = "wind_kph";
    private static final String PM2_5_PARAM = "pm2_5";

    @Autowired
    public WeatherService(UserConfigurationRepository userConfigurationRepository, WeatherApiService weatherApiService,
                          TemperatureService temperatureService) {
        this.userConfigurationRepository = userConfigurationRepository;
        this.weatherApiService = weatherApiService;
        this.temperatureService = temperatureService;
    }

    public Weather getWeather(String userId) {
        UserConfiguration userConfiguration = userConfigurationRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Location locationProvided = userConfiguration.getLocation().orElseThrow(() -> new ResourceNotFoundException("No location provided"));
        if (locationProvided.latitude2().isPresent() && locationProvided.longitude2().isPresent()) {
            Weather weather1 = extractWeather(weatherApiService.getWeatherData(locationProvided.latitude(), locationProvided.longitude()));
            Weather weather2 = extractWeather(weatherApiService.getWeatherData(locationProvided.latitude2().get(), locationProvided.longitude2().get()));
            return combineWeather(weather1, weather2);
        }
        JsonNode data = weatherApiService.getWeatherData(locationProvided.latitude(), locationProvided.longitude());
        return extractWeather(data);
    }

    private Weather combineWeather(Weather weather1, Weather weather2) {
        Weather weather = new Weather();

        weather.setLocationName(weather1.getLocationName() + " and " + weather2.getLocationName());

        weather.setTemperatureCelsius(Math.min(weather1.getTemperatureCelsius(), weather2.getTemperatureCelsius()));

        weather.setTemperature(
                weather1.getTemperatureCelsius() < weather2.getTemperatureCelsius()
                        ? weather1.getTemperature()
                        : weather2.getTemperature()
        );

        weather.setForecast(
                weather1.getForecast().ordinal() > weather2.getForecast().ordinal()
                        ? weather1.getForecast()
                        : weather2.getForecast()
        );

        weather.setAirCondition(
                weather1.getAirCondition().ordinal() > weather2.getAirCondition().ordinal()
                        ? weather1.getAirCondition()
                        : weather2.getAirCondition()
        );
        return weather;
    }

    private Weather extractWeather(JsonNode data) {
        Weather weather = new Weather();
        JsonNode currentData = data.get(CURRENT_PARAM);

        String locationName = data.get(LOCATION_PARAM).get(NAME_PARAM).asText();
        weather.setLocationName(locationName);

        String forecast = currentData.get(CONDITION_PARAM).get(TEXT_PARAM).asText();
        weather.setForecast(Forecast.getForecast(forecast));

        double airCondition = currentData.get(AIR_QUALITY_PARAM).get(PM2_5_PARAM).asDouble();
        weather.setAirCondition(AirCondition.fromPM25(airCondition));

        double temperature = currentData.get(TEMP_C_PARAM).asDouble();
        weather.setTemperatureCelsius(temperature);

        double windSpeedKmPerHour = currentData.get(WIND_KPH_PARAM).asDouble();
        weather.setTemperature(Temperature.getTemperature(temperatureService.calculateSensedTemperature(temperature, windSpeedKmPerHour)));

        return weather;
    }
}
