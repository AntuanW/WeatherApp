package pl.edu.agh.to2.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.to2.example.model.Location;
import pl.edu.agh.to2.example.persistance.ClothesRepository;
import pl.edu.agh.to2.example.persistance.UserConfiguration;
import pl.edu.agh.to2.example.persistance.UserConfigurationRepository;
import pl.edu.agh.to2.example.wardrobe.Clothes;
import pl.edu.agh.to2.example.wardrobe.Wardrobe;
import pl.edu.agh.to2.example.weather.Weather;
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import pl.edu.agh.to2.example.exceptions.ResourceNotFoundException;
import pl.edu.agh.to2.example.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherServiceTest {

    @Mock
    private UserConfigurationRepository userConfigurationRepository;

    @Mock
    private WeatherApiService weatherApiService;

    @Mock
    private TemperatureService temperatureService;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWeatherWithSuccessSingleLocation() throws JsonProcessingException {
        String userId = "testUser";
        UserConfiguration userConfiguration = new UserConfiguration(userId);
        userConfiguration.setLocation(new Location(
                50.0619, 19.9367, Optional.empty(), Optional.empty()
        ));

        JsonNode weatherData = createMockWeatherData();
        Weather expectedWeather = createMockWeather();

        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.of(userConfiguration));
        when(weatherApiService.getWeatherData(
                userConfiguration.getLocation().get().latitude(),
                userConfiguration.getLocation().get().longitude())
        ).thenReturn(weatherData);

        Weather result = weatherService.getWeather(userId);

        assertNotNull(result);
        assertEquals(expectedWeather.getTemperature(), result.getTemperature());
        assertEquals(expectedWeather.getForecast(), result.getForecast());
        assertEquals(expectedWeather.getAirCondition(), result.getAirCondition());
        verify(userConfigurationRepository, times(1)).findByUserId(userId);
        verify(weatherApiService, times(1)).getWeatherData(
                userConfiguration.getLocation().get().latitude(),
                userConfiguration.getLocation().get().longitude()
        );
    }

    @Test
    void testGetWeatherWithSuccessDoubleLocation() throws JsonProcessingException {
        String userId = "testUser";
        UserConfiguration userConfiguration = new UserConfiguration(userId);
        userConfiguration.setLocation(new Location(
                50.0619, 19.9367, Optional.of(45.01), Optional.of(50.98)
        ));

        JsonNode weatherData = createMockWeatherData();
        Weather expectedWeather = createMockWeather();

        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.of(userConfiguration));
        when(weatherApiService.getWeatherData(
                userConfiguration.getLocation().get().latitude(),
                userConfiguration.getLocation().get().longitude())
        ).thenReturn(weatherData);

        when(weatherApiService.getWeatherData(
                userConfiguration.getLocation().get().latitude2().get(),
                userConfiguration.getLocation().get().longitude2().get())
        ).thenReturn(weatherData);

        Weather result = weatherService.getWeather(userId);

        assertNotNull(result);
        assertEquals(expectedWeather.getTemperature(), result.getTemperature());
        assertEquals(expectedWeather.getForecast(), result.getForecast());
        assertEquals(expectedWeather.getAirCondition(), result.getAirCondition());
        verify(userConfigurationRepository, times(1)).findByUserId(userId);
        verify(weatherApiService, times(1)).getWeatherData(
                userConfiguration.getLocation().get().latitude(),
                userConfiguration.getLocation().get().longitude()
        );
        verify(weatherApiService, times(1)).getWeatherData(
                userConfiguration.getLocation().get().latitude2().get(),
                userConfiguration.getLocation().get().longitude2().get()
        );

    }

    @Test
    void testGetCombinedWeather() throws JsonProcessingException{
        String userId = "testUser";
        UserConfiguration userConfiguration = new UserConfiguration(userId);
        userConfiguration.setLocation(new Location(
                50.0619, 19.9367, Optional.of(45.01), Optional.of(50.98)
        ));
        JsonNode weatherData = createMockWeatherData();
        JsonNode weatherData2 = createMockWeatherDataToCombine();

        Weather expectedWeather = createMockWeatherCombined();

        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.of(userConfiguration));
        when(weatherApiService.getWeatherData(
                userConfiguration.getLocation().get().latitude(),
                userConfiguration.getLocation().get().longitude())
        ).thenReturn(weatherData);

        when(weatherApiService.getWeatherData(
                userConfiguration.getLocation().get().latitude2().get(),
                userConfiguration.getLocation().get().longitude2().get())
        ).thenReturn(weatherData2);

        Weather result = weatherService.getWeather(userId);

        verify(weatherApiService, times(2)).getWeatherData(
                anyDouble(),
                anyDouble()
        );

        assertNotNull(result);
        assertEquals(expectedWeather.getTemperature(), result.getTemperature());
        assertEquals(expectedWeather.getForecast(), result.getForecast());
        assertEquals(expectedWeather.getAirCondition(), result.getAirCondition());
    }

    @Test
    void testGetWeatherWithUserNotFoundException() {
        String userId = "nonexistentUser";
        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> weatherService.getWeather(userId));

        verify(userConfigurationRepository, times(1)).findByUserId(userId);
        verify(weatherApiService, never()).getWeatherData(anyDouble(), anyDouble());
    }

    @Test
    void testGetWeatherWithResourceNotFoundException() {
        String userId = "testUser";
        UserConfiguration userConfiguration = new UserConfiguration(userId);
        userConfiguration.setLocation(null);
        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.of(userConfiguration));

        assertThrows(ResourceNotFoundException.class, () -> weatherService.getWeather(userId));

        verify(userConfigurationRepository, times(1)).findByUserId(userId);
        verify(weatherApiService, never()).getWeatherData(anyDouble(), anyDouble());
    }

    private JsonNode createMockWeatherData() throws JsonProcessingException {
        String json = "{ \"location\": { \"name\": \"CityName\" }, \"current\": { \"temp_c\": 3.0, \"condition\": { \"text\": \"Sunny\" }, \"air_quality\": { \"pm2_5\": 2 }, \"wind_kph\": 0.1 } }";
        return readTree(json);
    }

    private JsonNode createMockWeatherDataToCombine() throws JsonProcessingException {
        String json = "{ \"location\": { \"name\": \"CityName\" }, \"current\": { \"temp_c\": 11.0, \"condition\": { \"text\": \"Rainy\" }, \"air_quality\": { \"pm2_5\": 90 }, \"wind_kph\": 0.1 } }";
        return readTree(json);
    }

    private Weather createMockWeather() {
        Weather weather = new Weather();
        weather.setTemperature(Temperature.FREEZING);
        weather.setTemperatureCelsius(3.0);
        weather.setForecast(Forecast.getForecast("Sunny"));
        weather.setAirCondition(AirCondition.fromPM25(2));
        return weather;
    }

    private Weather createMockWeatherCombined() {
        Weather weather = new Weather();
        weather.setTemperature(Temperature.FREEZING);
        weather.setTemperatureCelsius(3.0);
        weather.setForecast(Forecast.getForecast("Rainy"));
        weather.setAirCondition(AirCondition.fromPM25(90));
        return weather;
    }

    private JsonNode readTree(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(json);
    }
}