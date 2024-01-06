package pl.edu.agh.to2.example.controller.payload;

import java.time.LocalTime;

public record LocationRequest(
        double latitude,
        double longitude,
        LocalTime time
) {
}
