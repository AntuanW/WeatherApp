package pl.edu.agh.to2.example.weather.measures;

public enum Temperature {
    FREEZING,
    COLD,
    WARM,
    HOT;

    public static Temperature getTemperature(double temperature) {
        if (temperature < 3.0) {
            return FREEZING;
        } else if (temperature < 10.0) {
            return COLD;
        } else if (temperature < 20.0) {
            return WARM;
        } else {
            return HOT;
        }
    }
}
