package pl.edu.agh.to2.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.exceptions.ExternalApiException;
import pl.edu.agh.to2.example.model.Location;
import java.net.URI;
import java.text.DecimalFormat;
import java.text.NumberFormat;

@Service
public class WeatherApiService {
    private static final String WEATHER_API_URL_BASE = "https://api.weatherapi.com/v1/current.json";
    private static final String API_KEY = "2ea04a19ce374691987155332232411";
    private static final String AQI_PARAM = "aqi=yes";
    private final NumberFormat locationFormatter = new DecimalFormat("0.####");

    private String buildApiURL(Location location) {
        String loc = locationFormatter.format(location.lattitude()) + "," + locationFormatter.format(location.longitude());
        return String.format("%s?key=%s&q=%s&%s", WEATHER_API_URL_BASE, API_KEY, loc, AQI_PARAM);
    }

    private JsonNode requestWeather(HttpGet request, CloseableHttpClient httpClient) {
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new ExternalApiException("Something went wrong with getting weather data.");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(response.getEntity().getContent()).get("current");
        } catch (Exception e) {
            throw new ExternalApiException(e);
        }
    }

    public JsonNode getWeatherData(Location location) {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            URI uri = new URI(buildApiURL(location));
            HttpGet request = new HttpGet(uri);
            return requestWeather(request, httpClient);
        } catch(Exception e) {
            throw new ExternalApiException(e);
        }
    }
}
