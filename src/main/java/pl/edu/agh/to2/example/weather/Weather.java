package pl.edu.agh.to2.example.weather;

import com.fasterxml.jackson.databind.JsonNode;
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

public class Weather {

    private Forecast forecast;
    private Temperature temperature;
    private AirCondition airCondition;

    public Weather(JsonNode forecast, JsonNode temperature, JsonNode airCondition) {
        double temp = temperature.asDouble();
        double pm2_5 = airCondition.get("pm2_5").asDouble();
        String fc = forecast.asText();

        this.temperature = Temperature.getTemperature(temp);
        this.airCondition = AirCondition.fromPM2_5(pm2_5);
        this.forecast = Forecast.getForecast(fc);
    }

}
