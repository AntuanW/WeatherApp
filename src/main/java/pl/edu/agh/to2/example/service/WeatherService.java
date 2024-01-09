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
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WeatherService {
    private static final String CURRENT_PARAM = "current";
    private static final String NAME_PARAM = "name";
    private static final String TEXT_PARAM = "text";
    private static final String LOCATION_PARAM = "location";
    private static final String CONDITION_PARAM = "condition";
    private static final String AIR_QUALITY_PARAM = "air_quality";
    private static final String TEMP_C_PARAM = "temp_c";
    private static final String WIND_KPH_PARAM = "wind_kph";
    private static final String PM2_5_PARAM = "pm2_5";
    private static final String FORECAST_PARAM = "forecast";
    private static final String FORECASTDAY_PARAM = "forecastday";
    private static final String HOUR_PARAM = "hour";
    private final UserConfigurationRepository userConfigurationRepository;
    private final WeatherApiService weatherApiService;
    private final TemperatureService temperatureService;

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

        if (locationsProvided.isEmpty()) throw new ResourceNotFoundException("No location provided");

        List<Weather> weathers = locationsProvided.stream()
                .map(location -> extractWeather(weatherApiService.getWeatherData(location), location.time()))
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

    private Weather extractWeather(JsonNode data, LocalTime forecastTime) {
        Weather weather = new Weather();
        JsonNode currentData = data.get(CURRENT_PARAM);

        String locationName = data.get(LOCATION_PARAM).get(NAME_PARAM).asText();
        weather.setLocationName(locationName);

        double airCondition = currentData.get(AIR_QUALITY_PARAM).get(PM2_5_PARAM).asDouble();
        weather.setAirCondition(AirCondition.fromPM25(airCondition));

        JsonNode forecastData = data.get(FORECAST_PARAM).get(FORECASTDAY_PARAM);

        // If forecast time is before current time, it means that we have to check forecast for the next day
        LocalTime currentTime = LocalTime.now();
        int searchTime = forecastTime.truncatedTo(ChronoUnit.HOURS).getHour();
        int day = forecastTime.isBefore(currentTime) ? 1 : 0;
        forecastData = forecastData.get(day).get(HOUR_PARAM).get(searchTime);

        String forecast = forecastData.get(CONDITION_PARAM).get(TEXT_PARAM).asText();
        weather.setForecast(Forecast.getForecast(forecast));

        double temperature = forecastData.get(TEMP_C_PARAM).asDouble();
        weather.setTemperatureCelsius(temperature);

        double windSpeedKmPerHour = forecastData.get(WIND_KPH_PARAM).asDouble();
        weather.setTemperature(Temperature.getTemperature(temperatureService.calculateSensedTemperature(temperature, windSpeedKmPerHour)));
        return weather;
    }
}
