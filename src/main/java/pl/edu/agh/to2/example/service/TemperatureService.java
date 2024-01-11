package pl.edu.agh.to2.example.service;

import org.springframework.stereotype.Service;

@Service
public class TemperatureService {
    private static final double BASE_CONSTANT = 13.12;
    private static final double TEMPERATURE_CONSTANT = 0.6215;
    private static final double WIND_SPEED_CONSTANT1 = 11.37;
    private static final double WIND_SPEED_CONSTANT2 = 0.16;
    private static final double WIND_CHILL_CONSTANT = 0.3965;

    public double calculateSensedTemperature(double temperature, double windSpeedKmPerHour) {
        double sensedTemperature = BASE_CONSTANT +
                TEMPERATURE_CONSTANT * temperature -
                WIND_SPEED_CONSTANT1 * Math.pow(windSpeedKmPerHour, WIND_SPEED_CONSTANT2) +
                WIND_CHILL_CONSTANT * temperature * Math.pow(windSpeedKmPerHour, WIND_SPEED_CONSTANT2);
        return Math.round(sensedTemperature * 1000.0) / 1000.0;
    }
}
