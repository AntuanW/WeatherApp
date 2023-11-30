package pl.edu.agh.to2.example.weather;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.utils.LocationRequest;
import pl.edu.agh.to2.example.wardrobe.Wardrobe;
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;
import java.util.logging.Logger;

@Service
public class WeatherService {

    private final WeatherApiService weatherApiService;
    private Weather weather;
    private Wardrobe wardrobe;

    private final Logger logger = Logger.getLogger(getClass().getName());

    public WeatherService() {
        weatherApiService = new WeatherApiService();
        weather = new Weather();
    }

    public void setWeatherData(LocationRequest locationRequest) throws Exception {

        String lat;
        String lon;
        String locationString = "0.0,0.0";
        try {
            lat = Double.toString(locationRequest.latitude());
            lon = Double.toString(locationRequest.longitude());
            locationString = lat + "," + lon;
        } catch (NumberFormatException e) {
            logger.info("Cannot convert number to string.");
            throw new Exception();
        }

        weatherApiService.setWeatherApiURL(locationString);

        try{
            JsonNode weatherData = weatherApiService.getWeatherData();
            double temp = weatherData.get("temp_c").asDouble();
            weather.setTemperature(Temperature.getTemperature(temp));
            weather.setTemperatureCelsius(temp);

            String forecast = weatherData.get("condition").get("text").asText();
            weather.setForecast(Forecast.getForecast(forecast));

            double airCondition = weatherData.get("air_quality").get("pm2_5").asDouble();
            weather.setAirCondition(AirCondition.fromPM25(airCondition));
        } catch (Exception e) {
            logger.info("Something went wrong with getting weather data.");
        }
    }

    public void setWardrobe() {
        wardrobe = weather.getTemperature().getWardrobe();
        wardrobe.setUmbrella(wardrobe.checkUmbrella(weather.getForecast()));
        wardrobe.setGasMask(wardrobe.checkGasMask(weather.getAirCondition()));
    }

    public Temperature getTemperature() {
        return weather.getTemperature();
    }

    public Forecast getForecast() {
        return weather.getForecast();
    }

    public AirCondition getAirCondition() {
        return weather.getAirCondition();
    }

    public double getTempCelsius() {
        return weather.getTemperatureCelsius();
    }

    public Wardrobe getRightWardrobe() {
        setWardrobe();
        return wardrobe;
    }
}
