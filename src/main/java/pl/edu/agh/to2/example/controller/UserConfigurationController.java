package pl.edu.agh.to2.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.to2.example.controller.payload.LocationRequest;
import pl.edu.agh.to2.example.controller.payload.UserResponse;
import pl.edu.agh.to2.example.exceptions.UserNotFoundException;
import pl.edu.agh.to2.example.model.Location;
import pl.edu.agh.to2.example.persistance.UserConfiguration;
import pl.edu.agh.to2.example.persistance.UserConfigurationRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@RestController()
@CrossOrigin()
@RequestMapping(path = "/users/configuration")
public class UserConfigurationController {
    private static final Logger logger = Logger.getLogger(String.valueOf(UserConfigurationController.class));

    private final UserConfigurationRepository userConfigurationRepository;
    @Autowired
    public UserConfigurationController(UserConfigurationRepository userConfigurationRepository) {
        this.userConfigurationRepository = userConfigurationRepository;
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponse> initializeUser(
    ) {
        String userToken = UUID.randomUUID().toString();
        UserConfiguration userConfiguration = userConfigurationRepository
                .findByUserId(userToken).orElse(new UserConfiguration(userToken));
        userConfigurationRepository.saveUserConfiguration(userConfiguration);
        return ResponseEntity.ok().body(new UserResponse(userToken));
    }

    @PostMapping("/checkUser")
    public ResponseEntity<String> checkUser(
            @RequestHeader("Authorization") Optional<String> userToken
    ) {
        System.out.println(userToken);
        String token = userToken.orElseThrow(() -> new UserNotFoundException(""));
        userConfigurationRepository.findByUserId(token).orElseThrow(() -> new UserNotFoundException(token));
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/location")
    public ResponseEntity<String> postLocation(
            @RequestHeader("Authorization") String userToken,
            @RequestBody List<LocationRequest> locationRequests
    ) {
        try {
            logger.info(() -> "Message: " + locationRequests.size());
            UserConfiguration userConfiguration = userConfigurationRepository
                    .findByUserId(userToken).orElseThrow(() -> new UserNotFoundException("User not found"));

            locationRequests.stream()
                            .map(locationRequest -> new Location(locationRequest.latitude(), locationRequest.longitude()))
                                    .forEach(userConfiguration::addLocation);

            userConfigurationRepository.saveUserConfiguration(userConfiguration);
            return ResponseEntity.ok().body("User location successfully saved");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong with posting location.");
        }
    }
}
