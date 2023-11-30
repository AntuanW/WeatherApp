package pl.edu.agh.to2.example.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userId) {
        super("User " + userId + " not found");
    }
}
