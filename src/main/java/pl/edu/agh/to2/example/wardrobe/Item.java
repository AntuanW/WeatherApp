package pl.edu.agh.to2.example.wardrobe;

import pl.edu.agh.to2.example.weather.measures.Temperature;

import java.util.List;

public enum Item {
    // SHOES
    BOOTS,
    SNEAKERS,
    SANDALS,
    // Accessories
    SUNGLASSES,
    BASEBALL_CAP,
    HAT,
    WINTER_CAP,
    SCARF,
    GLOVES,
    // Trousers
    TROUSERS,
    SHORTS,
    // TOP
    T_SHIRT,
    JUMPER,
    CARDIGAN,
    LIGHT_JACKET,
    JACKET;

    public static List<Item> getAccessories(Temperature temperature) {
        return switch (temperature) {
            case FREEZING -> List.of(SCARF, GLOVES, WINTER_CAP);

            case COLD -> List.of(SCARF);

            case WARM -> List.of(HAT, BASEBALL_CAP);

            case HOT -> List.of(SUNGLASSES, HAT, BASEBALL_CAP);

        };
    }

    public static Item getTrousers(Temperature temperature) {
        return switch (temperature) {
            case FREEZING, WARM, COLD -> TROUSERS;

            case HOT -> SHORTS;

        };
    }

    public static List<Item> getTop(Temperature temperature) {
        return switch (temperature) {
            case FREEZING -> List.of(JUMPER, CARDIGAN, JACKET);

            case COLD -> List.of(JUMPER, LIGHT_JACKET);

            case WARM -> List.of(T_SHIRT, CARDIGAN);

            case HOT -> List.of(T_SHIRT);

        };
    }

    public static Item getShoes(Temperature temperature) {
        return switch (temperature) {
            case FREEZING, COLD -> BOOTS;

            case WARM -> SNEAKERS;

            case HOT -> SANDALS;

        };
    }
}
