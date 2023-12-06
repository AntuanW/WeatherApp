package pl.edu.agh.to2.example.weather.measures;

public enum AirCondition {
    VERY_GOOD,
    GOOD,
    PASSABLE,
    OKAY,
    UNHEALTHY,
    VERY_UNHEALTHY;

    public static AirCondition fromPM25(double pm2) {
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
