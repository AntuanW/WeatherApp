package pl.edu.agh.to2.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.exceptions.ResourceNotFoundException;
import pl.edu.agh.to2.example.exceptions.UserNotFoundException;
import pl.edu.agh.to2.example.model.Location;
import pl.edu.agh.to2.example.persistance.UserConfiguration;
import pl.edu.agh.to2.example.persistance.UserConfigurationRepository;
import pl.edu.agh.to2.example.weather.Weather;
import pl.edu.agh.to2.example.weather.measures.*;

import java.util.*;

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
        List<Location> locationsProvided = userConfiguration.getLocations();

        if(locationsProvided.isEmpty()) throw new ResourceNotFoundException("No location provided");

        List<Weather> weathers = locationsProvided.stream()
                .map(location -> extractWeather(weatherApiService.getWeatherData(location)))
                .toList();

        return combineWeather(weathers);
    }

    private Weather combineWeather(List<Weather> weathers) {
        Weather weather = new Weather();

        StringBuilder locationName = new StringBuilder(weathers.get(0).getLocationName());

        for (int i = 1; i < weathers.size(); i++) {
            locationName.append(" and ");
            locationName.append(weathers.get(i).getLocationName());
        }

        weather.setLocationName(locationName.toString());

        Weather minimumTemperatureWeather = weathers.stream()
                        .min(Comparator.comparing(Weather::getTemperatureCelsius))
                        .orElseThrow(NoSuchElementException::new);

        weather.setTemperatureCelsius(minimumTemperatureWeather.getTemperatureCelsius());

        weather.setTemperature(minimumTemperatureWeather.getTemperature());

        Forecast forecast = weathers.stream()
                        .max(Comparator.comparing(w -> w.getForecast().ordinal()))
                        .map(Weather::getForecast)
                        .orElseThrow(NoSuchElementException::new);

        weather.setForecast(forecast);

        AirCondition airCondition = weathers.stream()
                .max(Comparator.comparing(w -> w.getAirCondition().ordinal()))
                .map(Weather::getAirCondition)
                .orElseThrow(NoSuchElementException::new);

        weather.setAirCondition(airCondition);

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
