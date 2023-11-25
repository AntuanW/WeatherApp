package pl.edu.agh.to2.example.weather;

<<<<<<< HEAD
import com.fasterxml.jackson.databind.JsonNode;
=======
>>>>>>> 88c72fde0e9388a554e0796b9df90cd7a21a36f9
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

public class Weather {

    private Forecast forecast;
    private Temperature temperature;
    private AirCondition airCondition;

<<<<<<< HEAD
    public Weather(JsonNode forecast, JsonNode temperature, JsonNode airCondition) {
        double temp = temperature.asDouble();
        double pm2_5 = airCondition.get("pm2_5").asDouble();
        String fc = forecast.asText();

        this.temperature = Temperature.getTemperature(temp);
        this.airCondition = AirCondition.fromPM2_5(pm2_5);
        this.forecast = Forecast.getForecast(fc);
    }

=======
>>>>>>> 88c72fde0e9388a554e0796b9df90cd7a21a36f9
}
