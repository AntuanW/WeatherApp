package pl.edu.agh.to2.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.agh.to2.example.model.Location;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherApiServiceTest {

    @Mock
    private CloseableHttpClient httpClient;

    @Mock
    private CloseableHttpResponse response;

    @Mock
    private HttpEntity entity;

    @InjectMocks
    private WeatherApiService weatherApiService;

    @Test
    void testGetWeatherData() throws Exception {
        String json = "{\"location\":{\"name\":\"Krakow\"},\"current\":{\"temp_c\":11.0}}";
        ByteArrayInputStream content = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));

        when(httpClient.execute(any(HttpGet.class))).thenReturn(response);

        StatusLine statusLine = new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK");
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(content);

        Location location = new Location(50.0619, 19.9367, LocalTime.now());
        JsonNode result = weatherApiService.getWeatherData(location);

        assertNotNull(result);
    }
}