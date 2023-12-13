package pl.edu.agh.to2.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.exceptions.ResourceNotFoundException;
import pl.edu.agh.to2.example.persistance.ClothesRepository;
import pl.edu.agh.to2.example.wardrobe.Wardrobe;
import pl.edu.agh.to2.example.weather.Weather;

@Service
public class WardrobeService {
    private final WeatherService weatherService;
    private final ClothesRepository clothesRepository;

    @Autowired
    public WardrobeService(WeatherService weatherService, ClothesRepository clothesRepository) {
        this.weatherService = weatherService;
        this.clothesRepository = clothesRepository;
    }

    public Wardrobe getRightWardrobe(String userId) {
        Weather weather = weatherService.getWeather(userId);
        return pickWardrobe(weather);
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
