package pl.edu.agh.to2.example.persistance;

import pl.edu.agh.to2.example.model.Location;

import java.util.Optional;

public class UserConfiguration {

    private final String userId;
    private Location location;

    public UserConfiguration(String userId) {
        this.userId = userId;
    }

    public Optional<Location> getLocation() {
        return Optional.ofNullable(location);
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getUserId() {
        return userId;
    }
}
