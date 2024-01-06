package pl.edu.agh.to2.example.model;

import java.time.LocalTime;

public record Location(
        double latitude,
        double longitude,
        LocalTime time
) {
}