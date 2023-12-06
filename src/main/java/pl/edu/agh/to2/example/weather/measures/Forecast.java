package pl.edu.agh.to2.example.weather.measures;

public enum Forecast {
    CLEAR,
    CLOUDY,
    FOGGY,
    THUNDERY,
    RAINY,
    SLEETY,
    SNOWY,
    HAIL;

    public static Forecast getForecast(String forecast) {
        forecast = forecast.toLowerCase();

        if (forecast.contains("clear") || forecast.contains("sunny")) {
            return CLEAR;
        }

        if (forecast.contains("fog") || forecast.contains("mist")) {
            return FOGGY;
        }

        if (forecast.contains("sleet")) {
            return SLEETY;
        }

        if (forecast.contains("thundery")) {
            return THUNDERY;
        }

        if (forecast.contains("cloudy") || forecast.contains("overcast")) {
            return CLOUDY;
        }

        if (forecast.contains("ice pellets")) {
            return HAIL;
        }

        if (forecast.contains("snow") || forecast.contains("blizzard")) {
            return SNOWY;
        }
        return RAINY;
    }
}
