package pl.edu.agh.to2.example.persistance;

import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.wardrobe.Clothes;
import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClothesRepository {
    //w przyszłości wstrzyknięte z bazy, pewnie osobne tabele dla accessories, shoes, trousers i top.
    // W tabelach kolumny "cloth" i "temperature".
    Map<Temperature, Clothes> clothesMap = Map.of(

            Temperature.HOT, new Clothes("Sandals", "Shorts",
                    List.of("T-shirt"),
                    List.of("Sunglasses", "Hat", "Baseball cap")),
            Temperature.WARM, new Clothes("Sneakers", "Trousers",
                    List.of("T-shirt", "Cardigan"),
                    List.of("Hat", "Baseball cap")),
            Temperature.COLD, new Clothes("Boots", "Trousers",
                    List.of("Jumper", "Light jacket"),
                    List.of("Scarf")),
            Temperature.FREEZING, new Clothes("Boots", "Trousers",
                    List.of("Jumper", "Cardigan", "Jacket"),
                    List.of("Scarf", "Gloves", "Winter cap"))
    );

    public Optional<Clothes> getByTemperature(Temperature temperature) {
        return Optional.ofNullable(clothesMap.get(temperature));
    }
}
