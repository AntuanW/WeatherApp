package pl.edu.agh.to2.example.weather.measures;

import com.fasterxml.jackson.databind.JsonNode;

public enum AirCondition {
    VERY_GOOD,
    GOOD,
    OKAY,
    PASSABLE,
    UNHEALTHY,
    VERY_UNHEALTHY;

    //from pm2 to enum
    public static AirCondition fromPM2_5(double pm2) {
        if (pm2 < 12) {
            return VERY_GOOD;
        } else if (pm2 < 36) {
            return GOOD;
        } else if (pm2 < 60) {
            return OKAY;
        } else if (pm2 < 84) {
            return PASSABLE;
        } else if (pm2 < 180) {
            return UNHEALTHY;
        } else {
            return VERY_UNHEALTHY;
        }
    }
}
