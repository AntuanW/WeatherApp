package pl.edu.agh.to2.example.persistance;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserConfigurationRepository {
    private final Map<String, UserConfiguration> usersConfigurationMap = new HashMap<>();

    public Optional<UserConfiguration> findByUserId(String userID) {
        return Optional.ofNullable(usersConfigurationMap.get(userID));
    }

    public void saveUserConfiguration(UserConfiguration userConfiguration) {
        usersConfigurationMap.put(userConfiguration.getUserId(), userConfiguration);
    }
}
