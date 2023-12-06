package pl.edu.agh.to2.example.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.edu.agh.to2.example.exceptions.ResourceNotFoundException;
import pl.edu.agh.to2.example.exceptions.UserNotFoundException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ControllerAdvisorTest {

    @InjectMocks
    private ControllerAdvisor controllerAdvisor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleResourceNotFoundException() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");
        ResponseEntity<String> responseEntity = controllerAdvisor.handleResourceNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Resource not found", responseEntity.getBody());
    }

    @Test
    void testHandleUserNotFoundException() {
        String userToken = UUID.randomUUID().toString();
        UserNotFoundException exception = new UserNotFoundException(userToken);
        ResponseEntity<String> responseEntity = controllerAdvisor.handleUserNotFoundException(exception);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("User " + userToken + " not found", responseEntity.getBody());
    }
}