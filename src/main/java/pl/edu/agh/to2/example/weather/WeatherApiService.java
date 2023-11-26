package pl.edu.agh.to2.example.weather;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherApiService {
    private final static String weatherApiURLBase = "http://api.weatherapi.com/v1/current.json";
    private final static String apiKey = "2ea04a19ce374691987155332232411";
    private String weatherApiURL;
    private JsonNode weatherData;

    public WeatherApiService() {
    }

    public void setWeatherApiURL(String coords) {
        this.weatherApiURL = (weatherApiURLBase + "?key=" + apiKey + "&q=" + coords + "&aqi=yes");
    }

    //getter for weatherApiURL
    public String getWeatherApiURL() {
        return weatherApiURL;
    }

    // Getting weather data
    public void setWeatherData() throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(this.weatherApiURL)).build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            this.weatherData = objectMapper.readTree(response.body());
        } else {
            System.out.println("Error " + response.statusCode());
        }
    }

    // Getting data from JSON
    public JsonNode getTemperature() {
        return weatherData.get("current").get("temp_c");
    }

    public JsonNode getAirQuality() {
        return weatherData.get("current").get("air_quality").get("pm2_5");
    }

    public JsonNode getForecast() {
        return weatherData.get("current").get("condition").get("text");
    }
}
