package pl.edu.agh.to2.example.persistance;

import pl.edu.agh.to2.example.model.Location;

import java.util.LinkedList;
import java.util.List;
public class UserConfiguration {
    private final String userId;
    private List<Location> locations;

    public UserConfiguration(String userId) {
        this.userId = userId;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getUserId() {
        return userId;
    }
}
