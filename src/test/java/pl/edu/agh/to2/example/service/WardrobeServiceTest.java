package pl.edu.agh.to2.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.to2.example.persistance.ClothesRepository;

public class WardrobeServiceTest {

    @Mock
    private ClothesRepository clothesRepository;

    @Mock
    private WeatherService weatherService;


    @InjectMocks
    private WardrobeService wardrobeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


//        @Test
//    void testGetRightWardrobeWithSuccess() throws JsonProcessingException {
//        String userId = "testUser";
//        UserConfiguration userConfiguration = new UserConfiguration(userId);
//        userConfiguration.setLocation(new Location(
//                50.0619, 19.9367, Optional.empty(), Optional.empty()
//        ));
//        Clothes mockedClothes = new Clothes("Sandals", "Shorts",
//                List.of("T-shirt"), List.of("Sunglasses", "Hat", "Baseball cap"));
//
//        JsonNode weatherData = createMockWeatherData();
//        Weather expectedWeather = createMockWeather();
//
//        when(userConfigurationRepository.findByUserId(userId)).thenReturn(Optional.of(userConfiguration));
//        when(weatherApiService.getWeatherData(
//                userConfiguration.getLocation().get().latitude(),
//                userConfiguration.getLocation().get().longitude())
//        ).thenReturn(weatherData);
//        when(clothesRepository.getByTemperature(expectedWeather.getTemperature())).thenReturn(Optional.of(mockedClothes));
//
//        Wardrobe result = weatherService.getRightWardrobe(userId);
//
//        assertNotNull(result);
//        assertEquals(mockedClothes, result.getClothes());
//        assertFalse(result.getIfUmbrella());
//        assertFalse(result.getIfGasMask());
//        verify(userConfigurationRepository, times(1)).findByUserId(userId);
//        verify(weatherApiService, times(1)).getWeatherData(
//                userConfiguration.getLocation().get().latitude(),
//                userConfiguration.getLocation().get().longitude()
//        );
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
//        verify(weatherApiService, never()).getWeatherData(anyDouble(), anyDouble());
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
//        verify(weatherApiService, never()).getWeatherData(anyDouble(), anyDouble());
//        verify(clothesRepository, never()).getByTemperature(any());
//    }

}
