package pl.edu.agh.to2.example.weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class WeatherApiService {
    private static final String WEATHER_API_URL_BASE = "http://api.weatherapi.com/v1/current.json";
    private static final String API_KEY = "2ea04a19ce374691987155332232411";
    private static final String AQI_PARAM = "aqi=yes";
    private String weatherApiURL;

    private final Logger logger = Logger.getLogger(getClass().getName());


    public void setWeatherApiURL(String coords) {
        this.weatherApiURL = buildApiURL(coords);
    }

    private String buildApiURL(String coords) {
        return String.format("%s?key=%s&q=%s&%s", WEATHER_API_URL_BASE, API_KEY, coords, AQI_PARAM);
    }

    //getter for weatherApiURL
    public String getWeatherApiURL() {
        return weatherApiURL;
    }

    private JsonNode requestWeather(HttpGet request, CloseableHttpClient httpClient) throws Exception {
        try {
            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new Exception("Something went wrong with getting weather data.");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(response.getEntity().getContent()).get("current");
        } catch (Exception e) {
            logger.info("Something went wrong with getting weather data.");
            throw e;
        }
    }

    // Getting weather data
    public JsonNode getWeatherData() throws Exception {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            URI uri = new URI(this.weatherApiURL);
            HttpGet request = new HttpGet(uri);
            return requestWeather(request, httpClient);
        } catch (IOException | URISyntaxException e) {
            logger.info("Error closing http client.");
            throw e;
        }
    }
}
