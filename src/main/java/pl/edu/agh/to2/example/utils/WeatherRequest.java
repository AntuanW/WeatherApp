package pl.edu.agh.to2.example.utils;

import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

public record WeatherRequest(
        double temperature,
        AirCondition airQuality,
        Forecast forecast,
        Temperature temperatureScale
) {
}
