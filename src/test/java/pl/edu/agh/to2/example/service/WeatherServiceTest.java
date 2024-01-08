package pl.edu.agh.to2.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.to2.example.model.Location;
import pl.edu.agh.to2.example.persistance.UserConfiguration;
import pl.edu.agh.to2.example.persistance.UserConfigurationRepository;
import pl.edu.agh.to2.example.weather.Weather;
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import pl.edu.agh.to2.example.exceptions.ResourceNotFoundException;
import pl.edu.agh.to2.example.exceptions.UserNotFoundException;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
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
    void testGetWeatherWithSuccessSingleLocation() {
        String userId = "testUser";
        UserConfiguration userConfiguration = new UserConfiguration(userId);
        userConfiguration.setLocations(
                List.of(new Location(52.51, -0.11, LocalTime.now().plusMinutes(1)))
        );

        JsonNode weatherData = getMockWeather1Json();
        Weather expectedWeather = createMockWeather();

        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.of(userConfiguration));

        when(
                weatherApiService.getWeatherData(
                        userConfiguration.getLocations().get(0)
                )
        ).thenReturn(weatherData);

        Weather result = weatherService.getWeather(userId);

        assertNotNull(result);
        assertEquals(expectedWeather.getTemperature(), result.getTemperature());
        assertEquals(expectedWeather.getForecast(), result.getForecast());
        assertEquals(expectedWeather.getAirCondition(), result.getAirCondition());
        verify(userConfigurationRepository, times(1)).findByUserId(userId);
        verify(weatherApiService, times(1)).getWeatherData(
                userConfiguration.getLocations().get(0)
        );
    }

    @Test
    void testGetWeatherWithSuccessMultipleLocation() {
        String userId = "testUser";
        UserConfiguration userConfiguration = new UserConfiguration(userId);
        userConfiguration.setLocations(List.of(
                new Location(52.51, -0.11, LocalTime.now().plusMinutes(1)),
                new Location(69.69, 4.20, LocalTime.now().plusMinutes(1)),
                new Location(52.51, -0.11, LocalTime.now().plusMinutes(1)),
                new Location(69.69, 4.20, LocalTime.now().plusMinutes(1)),
                new Location(52.51, -0.11, LocalTime.now().plusMinutes(1))
                ));

        JsonNode weatherData1 = getMockWeather1Json();
        JsonNode weatherData2 = getMockWeather2Json();
        Weather expectedCombinedWeather = createCombinedMockWeather();

        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.of(userConfiguration));
        when(weatherApiService.getWeatherData(
                userConfiguration.getLocations().get(0)
        )).thenReturn(weatherData1);

        when(weatherApiService.getWeatherData(
                userConfiguration.getLocations().get(1)
        )).thenReturn(weatherData2);

        when(weatherApiService.getWeatherData(
                userConfiguration.getLocations().get(2)
        )).thenReturn(weatherData1);

        when(weatherApiService.getWeatherData(
                userConfiguration.getLocations().get(3)
        )).thenReturn(weatherData2);

        when(weatherApiService.getWeatherData(
                userConfiguration.getLocations().get(4)
        )).thenReturn(weatherData1);

        Weather result = weatherService.getWeather(userId);

        assertNotNull(result);
        assertEquals(expectedCombinedWeather.getTemperature(), result.getTemperature());
        assertEquals(expectedCombinedWeather.getForecast(), result.getForecast());
        assertEquals(expectedCombinedWeather.getAirCondition(), result.getAirCondition());

        verify(userConfigurationRepository, times(1)).findByUserId(userId);

        verify(weatherApiService, times(1)).getWeatherData(
                userConfiguration.getLocations().get(0)
        );
        verify(weatherApiService, times(2)).getWeatherData(
                userConfiguration.getLocations().get(1)
        );
        verify(weatherApiService, times(2)).getWeatherData(
                userConfiguration.getLocations().get(2)
        );
        verify(weatherApiService, times(2)).getWeatherData(
                userConfiguration.getLocations().get(3)
        );
        verify(weatherApiService, times(2)).getWeatherData(
                userConfiguration.getLocations().get(4)
        );
    }

    @Test
    void testGetWeatherWithUserNotFoundException() {
        String userId = "nonexistentUser";
        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> weatherService.getWeather(userId));

        verify(userConfigurationRepository, times(1)).findByUserId(userId);
        verify(weatherApiService, never()).getWeatherData(any(Location.class));
    }

    @Test
    void testGetWeatherWithResourceNotFoundException() {
        String userId = "testUser";
        UserConfiguration userConfiguration = new UserConfiguration(userId);
        userConfiguration.setLocations(List.of());
        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.of(userConfiguration));

        assertThrows(ResourceNotFoundException.class, () -> weatherService.getWeather(userId));

        verify(userConfigurationRepository, times(1)).findByUserId(userId);
        verify(weatherApiService, never()).getWeatherData(any(Location.class));
    }

    private JsonNode getMockWeather1Json() {
        return getMockWeatherDataFromJSON("mockWeather1.json");
    }

    private JsonNode getMockWeather2Json() {
        return getMockWeatherDataFromJSON("mockWeather2.json");
    }

    private JsonNode getMockWeatherDataFromJSON(String pathName){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.createObjectNode();
        try {
            jsonNode = objectMapper.readTree(new File("src/test/java/pl/edu/agh/to2/example/service/mockWeathers/" + pathName));
            return jsonNode;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }

    private Weather createMockWeather() {
        Weather weather = new Weather();
        weather.setTemperature(Temperature.FREEZING);
        weather.setTemperatureCelsius(0.2);
        weather.setForecast(Forecast.getForecast("Partly cloudy"));
        weather.setAirCondition(AirCondition.fromPM25(0.7));
        return weather;
    }

    private Weather createCombinedMockWeather() {
        Weather weather = new Weather();
        weather.setTemperature(Temperature.FREEZING);
        weather.setTemperatureCelsius(0.2);
        weather.setForecast(Forecast.getForecast("Rainy"));
        weather.setAirCondition(AirCondition.fromPM25(200));
        return weather;
    }
}