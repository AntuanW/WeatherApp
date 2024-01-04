package pl.edu.agh.to2.example;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.agh.to2.example.model.Location;
import pl.edu.agh.to2.example.service.WeatherApiService;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


@SpringBootApplication
public class WeatherApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }
}
