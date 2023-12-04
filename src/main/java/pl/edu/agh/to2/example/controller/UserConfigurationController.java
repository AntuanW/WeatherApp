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
import java.util.logging.Logger;

@RestController()
@CrossOrigin()
@RequestMapping(path = "/users/configuration")
public class UserConfigurationController {
    private static final Logger logger = Logger.getLogger(String.valueOf(UserConfigurationController.class));

    @Autowired
    private UserConfigurationRepository userConfigurationRepository;

    @PostMapping("/user")
    public ResponseEntity<UserResponse> initializeUser(
    ) {
        String userToken = "aa";//UUID.randomUUID().toString();
        UserConfiguration userConfiguration = userConfigurationRepository
                .findByUserId(userToken).orElse(new UserConfiguration(userToken));
        userConfigurationRepository.saveUserConfiguration(userConfiguration);
        return ResponseEntity.ok().body(new UserResponse(userToken));
    }

    @PostMapping("/location")
    public ResponseEntity<String> postLocation(
            @RequestHeader("Authorization") String userToken,
            @RequestBody LocationRequest locationRequest
    ) {
        try {

            logger.info(() -> "Message: " + locationRequest.toString());
            UserConfiguration userConfiguration = userConfigurationRepository
                    .findByUserId(userToken).orElseThrow(() -> new UserNotFoundException("User not found"));
            Location location = new Location(locationRequest.latitude(), locationRequest.longitude());
            userConfiguration.setLocation(location);
            userConfigurationRepository.saveUserConfiguration(userConfiguration);
            return ResponseEntity.ok().body("User location successfully saved");
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong with getting weather data.");
        }

    }
}
