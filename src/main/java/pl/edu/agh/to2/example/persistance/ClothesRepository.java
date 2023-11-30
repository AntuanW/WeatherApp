package pl.edu.agh.to2.example.persistance;

import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.wardrobe.Clothes;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClothesRepository {
    Map<Temperature, Clothes> clothesMap = Map.of(
            Temperature.HOT, new Clothes(null, null, null, null),
            Temperature.WARM, new Clothes(null, null, null, null),
            Temperature.COLD, new Clothes(null, null, null, null),
            Temperature.FREEZING, new Clothes(null, null, null, null)
    );

    public Optional<Clothes> getByTemperature(Temperature temperature) {
        return Optional.ofNullable(clothesMap.get(temperature));
    }
}
