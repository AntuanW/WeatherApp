package pl.edu.agh.to2.example.controller.payload;

import java.util.Optional;

public record LocationRequest(
        double latitude,
        double longitude,
        Optional<Double> latitude2,
        Optional<Double> longitude2
) {
}
