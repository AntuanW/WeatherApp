package pl.edu.agh.to2.example.weather;

import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.utils.LocationRequest;
import pl.edu.agh.to2.example.wardrobe.Wardrobe;
import pl.edu.agh.to2.example.weather.measures.AirCondition;
import pl.edu.agh.to2.example.weather.measures.Forecast;
import pl.edu.agh.to2.example.weather.measures.Temperature;

@Service
public class WeatherService {

    private final WeatherApiService weatherApiService;
    private Weather weather;
    private Wardrobe wardrobe;

    public WeatherService() {
        weatherApiService = new WeatherApiService();
        weather = new Weather();
    }

    public void setWeatherData(LocationRequest locationRequest) {

        String lat, lon, locationString = "0.0,0.0";
        try {
            lat = Double.toString(locationRequest.latitude());
            lon = Double.toString(locationRequest.longitude());
            locationString = lat + "," + lon;
        } catch (NumberFormatException e) {
            System.err.println("Cannot convert number to string.");
        }

        weatherApiService.setWeatherApiURL(locationString);

        try {
            weatherApiService.setWeatherData();
        } catch (Exception e) {
            System.out.println("Something went wrong with getting weather data.");
        }

        double temp = weatherApiService.getTemperature().asDouble();
        weather.setTemperature(Temperature.getTemperature(temp));
        weather.setTemperatureCelsius(temp);

        String forecast = weatherApiService.getForecast().asText();
        weather.setForecast(Forecast.getForecast(forecast));

        double airCondition = weatherApiService.getAirQuality().asDouble();
        weather.setAirCondition(AirCondition.fromPM2_5(airCondition));

        System.out.println(temp + " " + forecast + " " + airCondition);
    }

    public void setWardrobe(){
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