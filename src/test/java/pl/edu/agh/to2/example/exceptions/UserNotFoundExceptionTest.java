package pl.edu.agh.to2.example.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNotFoundExceptionTest {
    private String errorMessage;
    private String userId;

    @BeforeEach
    void setUp() {
        errorMessage = "User testUser not found";
        userId = "testUser";
    }

    @Test
    void testConstructorAndMessage() {
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            throw new UserNotFoundException(userId);
        });

        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }
}