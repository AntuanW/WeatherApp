package pl.edu.agh.to2.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.to2.example.exceptions.ResourceNotFoundException;
import pl.edu.agh.to2.example.model.Location;
import pl.edu.agh.to2.example.persistance.ClothesRepository;
import pl.edu.agh.to2.example.persistance.UserConfiguration;
import pl.edu.agh.to2.example.persistance.UserConfigurationRepository;
import pl.edu.agh.to2.example.wardrobe.Clothes;
import pl.edu.agh.to2.example.weather.Weather;
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WardrobeServiceTest {

    @Mock
    private ClothesRepository clothesRepository;

    @Mock
    private WeatherService weatherService;

    @Mock
    private Weather weather;
    @Mock
    private UserConfigurationRepository userConfigurationRepository;

    @InjectMocks
    private WardrobeService wardrobeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetRightWardrobeWithSuccess() throws JsonProcessingException {
        String userId = "testUser";
        UserConfiguration userConfiguration = new UserConfiguration(userId);
        userConfiguration.setLocation(new Location(
                50.0619, 19.9367, Optional.empty(), Optional.empty()
        ));

        Clothes mockedClothes = new Clothes("Sandals", "Shorts",
                List.of("T-shirt"), List.of("Sunglasses", "Hat", "Baseball cap"));

        when(weather.getTemperature()).thenReturn(Temperature.HOT);
        when(weather.getForecast()).thenReturn(Forecast.CLEAR);
        when(weather.getAirCondition()).thenReturn(AirCondition.GOOD);

        when(weatherService.getWeather(userId)).thenReturn(weather);
        when(clothesRepository.getByTemperature(Temperature.HOT)).thenReturn(Optional.of(mockedClothes));

        assertEquals(mockedClothes, wardrobeService.getRightWardrobe(userId).getClothes());
    }

    @Test
    void testGetRightWardrobeWithResourceNotFoundException() {
        String userId = "testUser";
        UserConfiguration userConfiguration = new UserConfiguration(userId);
        userConfiguration.setLocation(null);

        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.of(userConfiguration));

        when(weather.getTemperature()).thenReturn(Temperature.HOT);
        when(weather.getForecast()).thenReturn(Forecast.CLEAR);
        when(weather.getAirCondition()).thenReturn(AirCondition.GOOD);

        when(weatherService.getWeather(userId)).thenReturn(weather);

        assertThrows(ResourceNotFoundException.class, () -> wardrobeService.getRightWardrobe(userId));
    }

}
