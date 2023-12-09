package pl.edu.agh.to2.example.model;

import java.util.Optional;

public record Location(
        double latitude,
        double longitude,
        Optional<Double> latitude2,
        Optional<Double> longitude2
) {
}