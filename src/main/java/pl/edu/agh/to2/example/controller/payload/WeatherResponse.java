package pl.edu.agh.to2.example.controller.payload;

import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

public record WeatherResponse(
        double temperature,
        AirCondition airQuality,
        Forecast forecast,
        Temperature temperatureScale
) {
}
