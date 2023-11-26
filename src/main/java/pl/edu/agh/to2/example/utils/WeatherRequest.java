package pl.edu.agh.to2.example.utils;

import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;

public record WeatherRequest(
        double temperature,
        AirCondition airQuality,
        Forecast forecast
) {
}
