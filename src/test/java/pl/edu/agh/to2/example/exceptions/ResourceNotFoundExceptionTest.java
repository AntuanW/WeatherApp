package pl.edu.agh.to2.example.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {
    private String errorMessage;

    @BeforeEach
    void setUp() {
        errorMessage = "Resource not found";
    }

    @Test
    void testConstructorAndMessage() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            throw new ResourceNotFoundException(errorMessage);
        });

        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }
}