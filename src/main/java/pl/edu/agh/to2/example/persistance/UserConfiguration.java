package pl.edu.agh.to2.example.persistance;

import pl.edu.agh.to2.example.model.Location;

public class UserConfiguration {

    private final String userId;
    private Location location;

    public UserConfiguration(String userId) {
        this.userId = userId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getUserId() {
        return userId;
    }
}
