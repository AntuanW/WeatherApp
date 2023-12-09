//package pl.edu.agh.to2.example.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import pl.edu.agh.to2.example.model.Location;
//import pl.edu.agh.to2.example.persistance.ClothesRepository;
//import pl.edu.agh.to2.example.persistance.UserConfiguration;
//import pl.edu.agh.to2.example.persistance.UserConfigurationRepository;
//import pl.edu.agh.to2.example.wardrobe.Clothes;
//import pl.edu.agh.to2.example.wardrobe.Wardrobe;
//import pl.edu.agh.to2.example.weather.Weather;
//import pl.edu.agh.to2.example.weather.measures.AirCondition;
//import pl.edu.agh.to2.example.weather.measures.Forecast;
//import pl.edu.agh.to2.example.weather.measures.Temperature;
//
//import pl.edu.agh.to2.example.exceptions.ResourceNotFoundException;
//import pl.edu.agh.to2.example.exceptions.UserNotFoundException;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class WeatherServiceTest {
//
//    @Mock
//    private UserConfigurationRepository userConfigurationRepository;
//
//    @Mock
//    private WeatherApiService weatherApiService;
//
//    @Mock
//    private TemperatureService temperatureService;
//
//    @Mock
//    private ClothesRepository clothesRepository;
//
//    @InjectMocks
//    private WeatherService weatherService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetRightWardrobeWithSuccess() throws JsonProcessingException {
//        String userId = "testUser";
//        UserConfiguration userConfiguration = new UserConfiguration(userId);
//        userConfiguration.setLocation(new Location(50.0619, 19.9367));
//        Clothes mockedClothes = new Clothes("Sandals", "Shorts",
//                List.of("T-shirt"), List.of("Sunglasses", "Hat", "Baseball cap"));
//
//        JsonNode weatherData = createMockWeatherData();
//        Weather expectedWeather = createMockWeather();
//
//        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.of(userConfiguration));
//        when(weatherApiService.getWeatherData(userConfiguration.getLocation().orElseThrow())).thenReturn(weatherData);
//        when(clothesRepository.getByTemperature(expectedWeather.getTemperature())).thenReturn(Optional.of(mockedClothes));
//
//        Wardrobe result = weatherService.getRightWardrobe(userId);
//
//        assertNotNull(result);
//        assertEquals(mockedClothes, result.getClothes());
//        assertFalse(result.getIfUmbrella());
//        assertFalse(result.getIfGasMask());
//        verify(userConfigurationRepository, times(1)).findByUserId(userId);
//        verify(weatherApiService, times(1)).getWeatherData(userConfiguration.getLocation().orElseThrow());
//        verify(clothesRepository, times(1)).getByTemperature(expectedWeather.getTemperature());
//    }
//
//    @Test
//    void testGetRightWardrobeWithUserNotFoundException() {
//        String userId = "nonexistentUser";
//        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> weatherService.getRightWardrobe(userId));
//
//        verify(userConfigurationRepository, times(1)).findByUserId(userId);
//        verify(weatherApiService, never()).getWeatherData(any());
//        verify(clothesRepository, never()).getByTemperature(any());
//    }
//
//    @Test
//    void testGetRightWardrobeWithResourceNotFoundException() {
//        String userId = "testUser";
//        UserConfiguration userConfiguration = new UserConfiguration(userId);
//        userConfiguration.setLocation(null);
//        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.of(userConfiguration));
//
//        assertThrows(ResourceNotFoundException.class, () -> weatherService.getRightWardrobe(userId));
//
//        verify(userConfigurationRepository, times(1)).findByUserId(userId);
//        verify(weatherApiService, never()).getWeatherData(any());
//        verify(clothesRepository, never()).getByTemperature(any());
//    }
//
//    @Test
//    void testGetWeatherWithSuccess() throws JsonProcessingException {
//        String userId = "testUser";
//        UserConfiguration userConfiguration = new UserConfiguration(userId);
//        userConfiguration.setLocation(new Location(50.0619, 19.9367));
//
//        JsonNode weatherData = createMockWeatherData();
//        Weather expectedWeather = createMockWeather();
//
//        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.of(userConfiguration));
//        when(weatherApiService.getWeatherData(userConfiguration.getLocation().orElseThrow())).thenReturn(weatherData);
//
//        Weather result = weatherService.getWeather(userId);
//
//        assertNotNull(result);
//        assertEquals(expectedWeather.getTemperature(), result.getTemperature());
//        assertEquals(expectedWeather.getForecast(), result.getForecast());
//        assertEquals(expectedWeather.getAirCondition(), result.getAirCondition());
//        verify(userConfigurationRepository, times(1)).findByUserId(userId);
//        verify(weatherApiService, times(1)).getWeatherData(userConfiguration.getLocation().orElseThrow());
//    }
//
//    @Test
//    void testGetWeatherWithUserNotFoundException() {
//        String userId = "nonexistentUser";
//        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> weatherService.getWeather(userId));
//
//        verify(userConfigurationRepository, times(1)).findByUserId(userId);
//        verify(weatherApiService, never()).getWeatherData(any());
//    }
//
//    @Test
//    void testGetWeatherWithResourceNotFoundException() {
//        String userId = "testUser";
//        UserConfiguration userConfiguration = new UserConfiguration(userId);
//        userConfiguration.setLocation(null);
//        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.of(userConfiguration));
//
//        assertThrows(ResourceNotFoundException.class, () -> weatherService.getWeather(userId));
//
//        verify(userConfigurationRepository, times(1)).findByUserId(userId);
//        verify(weatherApiService, never()).getWeatherData(any());
//    }
//
//    private JsonNode createMockWeatherData() throws JsonProcessingException {
//        String json = "{ \"location\": { \"name\": \"CityName\" }, \"current\": { \"temp_c\": 3.0, \"condition\": { \"text\": \"Sunny\" }, \"air_quality\": { \"pm2_5\": 2 }, \"wind_kph\": 0.1 } }";
//        return readTree(json);
//    }
//
//    private Weather createMockWeather() {
//        Weather weather = new Weather();
//        weather.setTemperature(Temperature.FREEZING);
//        weather.setTemperatureCelsius(3.0);
//        weather.setForecast(Forecast.getForecast("Sunny"));
//        weather.setAirCondition(AirCondition.fromPM25(2));
//        return weather;
//    }
//
//    private JsonNode readTree(String json) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readTree(json);
//    }
//}