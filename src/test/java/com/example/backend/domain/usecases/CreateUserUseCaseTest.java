package com.example.backend.domain.usecases;

import com.example.backend.adapters.request_response.RegisterRequest;
import com.example.backend.domain.entities.User;
import com.example.backend.domain.enums.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Test
    public void testCreateUser() {
        // GIVEN
        RegisterRequest request = new RegisterRequest(
                "firstname",
                "lastname",
                "mail@example.com",
                "password123");

        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(request.password())).thenReturn(encodedPassword);

        // WHEN
        User user = createUserUseCase.createUser(request);

        // THEN
        assertEquals(request.firstname(), user.getFirstname());
        assertEquals(request.lastname(), user.getLastname());
        assertEquals(request.email(), user.getEmail());
        assertEquals(encodedPassword, user.getPassword());
        assertEquals(Role.USER, user.getRole());
    }
}