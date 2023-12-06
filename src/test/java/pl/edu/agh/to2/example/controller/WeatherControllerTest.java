package pl.edu.agh.to2.example.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.edu.agh.to2.example.controller.payload.WeatherResponse;
import pl.edu.agh.to2.example.service.WeatherService;
import pl.edu.agh.to2.example.wardrobe.Clothes;
import pl.edu.agh.to2.example.wardrobe.Wardrobe;
import pl.edu.agh.to2.example.weather.Weather;
import pl.edu.agh.to2.example.weather.measures.Forecast;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWardrobe() {
        String userToken = "testToken";
        Wardrobe expectedWardrobe = new Wardrobe();
        expectedWardrobe.setClothes(new Clothes("Sandals", "Shorts",
                List.of("T-shirt"), List.of("Sunglasses", "Hat", "Baseball cap")));

        when(weatherService.getRightWardrobe(userToken)).thenReturn(expectedWardrobe);

        ResponseEntity<Wardrobe> responseEntity = weatherController.getWardrobe(userToken);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedWardrobe, responseEntity.getBody());
        assertEquals(expectedWardrobe.getClothes(), Objects.requireNonNull(responseEntity.getBody()).getClothes());
        verify(weatherService, times(1)).getRightWardrobe(userToken);
    }

    @Test
    void testGetWeather() {
        String userToken = "testToken";
        Weather expectedWeather = new Weather();
        expectedWeather.setForecast(Forecast.CLEAR);
        when(weatherService.getWeather(userToken)).thenReturn(expectedWeather);

        ResponseEntity<WeatherResponse> responseEntity = weatherController.getWeather(userToken);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedWeather.getForecast(), Objects.requireNonNull(responseEntity.getBody()).forecast());
        verify(weatherService, times(1)).getWeather(userToken);
    }

    @Test
    void testGetWardrobeError() {
        String userToken = "testToken";
        when(weatherService.getRightWardrobe(userToken)).thenThrow(new RuntimeException("Test exception"));

        ResponseEntity<Wardrobe> responseEntity = weatherController.getWardrobe(userToken);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        verify(weatherService, times(1)).getRightWardrobe(userToken);
    }

    @Test
    void testGetWeatherError() {
        String userToken = "testToken";
        when(weatherService.getWeather(userToken)).thenThrow(new RuntimeException("Test exception"));

        ResponseEntity<WeatherResponse> responseEntity = weatherController.getWeather(userToken);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        verify(weatherService, times(1)).getWeather(userToken);
    }
}