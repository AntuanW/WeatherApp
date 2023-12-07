package pl.edu.agh.to2.example.controller.payload;

import java.util.Optional;

public record LocationRequest(
        double latitude,
        Optional<Double> latitude2,
        double longitude,
        Optional<Double> longitude2
) {
}
