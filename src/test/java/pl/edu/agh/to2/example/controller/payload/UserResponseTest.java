package pl.edu.agh.to2.example.controller.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserResponseTest {
    private UserResponse userResponse;
    private String token;

    @BeforeEach
    void setUp() {
        token = "token";
        userResponse = new UserResponse(token);
    }

    @Test
    void testToken() {
        assertEquals(token, userResponse.token());
    }
}