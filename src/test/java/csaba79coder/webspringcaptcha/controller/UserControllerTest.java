package csaba79coder.webspringcaptcha.controller;

import csaba79coder.webspringcaptcha.dto.RegisterUserRequest;
import csaba79coder.webspringcaptcha.model.Role;
import csaba79coder.webspringcaptcha.model.User;
import csaba79coder.webspringcaptcha.repository.UserRepository;
import csaba79coder.webspringcaptcha.util.AuthUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;


    @Test
    void getLoggedUserUnauthorized() {
        // given: not
        HttpEntity<String> requestWithoutCredentials = new HttpEntity<>("?");

        // when
        ResponseEntity<Object> response = restTemplate.exchange("/user", HttpMethod.GET, requestWithoutCredentials, Object.class);

        // then
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void getLoggedUser() {
        // given
        HttpEntity<String> requestWithCredentials = new HttpEntity<>(AuthUtil.getBasicAuthHeaders());

        // when
        ResponseEntity<Object> response = restTemplate.exchange("/user", HttpMethod.GET, requestWithCredentials, Object.class);

        // then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getUsers() {
        // given
        userRepository.save(User.builder()
                .username("user1")
                .email("testuser@test.com")
                .registrationDate(Timestamp.from(Instant.now()))
                .password(new BCryptPasswordEncoder().encode("password"))
                .role(Role.ROLE_USER)
                .enable(true)
                .build()
        );
        HttpEntity<String> requestWithCredentials = new HttpEntity<>(AuthUtil.getBasicAuthHeaders());

        // when
        ResponseEntity<User[]> response = restTemplate.exchange("/users", HttpMethod.GET, requestWithCredentials, User[].class);

        // then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        User[] responseBody = response.getBody();
        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(2, responseBody.length);
        Assertions.assertEquals("admin", responseBody[0].getUsername());
        Assertions.assertEquals("us" +
                "" +
                "" +
                "er1", responseBody[1].getUsername());
    }

    @Test
    void getUserByUsername() {
        // given
        String usernameToFind = "admin";
        HttpEntity<String> requestWithCredentials = new HttpEntity<>(AuthUtil.getBasicAuthHeaders());

        // when
        ResponseEntity<User> response = restTemplate.exchange("/users/" + usernameToFind, HttpMethod.GET, requestWithCredentials, User.class);

        // then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        User user = response.getBody();
        Assertions.assertNotNull(user);
        Assertions.assertEquals(usernameToFind, user.getUsername());
    }

    @Test
    void registerWithReservedUsername() {
        // given
        RegisterUserRequest newUserWithReservedUsername = RegisterUserRequest.builder()
                .username("admin")
                .password("password")
                .build();
        HttpEntity<RegisterUserRequest> requestWithCredentials = new HttpEntity<>(newUserWithReservedUsername, AuthUtil.getBasicAuthHeaders());

        // when
        ResponseEntity<String> response = restTemplate.exchange("/register", HttpMethod.POST, requestWithCredentials, String.class);

        // then
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        String responseMessage = response.getBody();
        Assertions.assertNotNull(responseMessage);
        Assertions.assertEquals("RuntimeException: username is already in use", responseMessage);
    }

    @Test
    void registerWithNotReservedUsername() {
        // given
        String newUsername = "admin2";
        RegisterUserRequest newUser = RegisterUserRequest.builder()
                .username(newUsername)
                .password("password")
                .build();
        HttpEntity<RegisterUserRequest> requestWithCredentials = new HttpEntity<>(newUser, AuthUtil.getBasicAuthHeaders());

        // when
        ResponseEntity<String> response = restTemplate.exchange("/register", HttpMethod.POST, requestWithCredentials, String.class);

        // then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        String responseMessage = response.getBody();
        Assertions.assertNotNull(responseMessage);
        Assertions.assertEquals("registered", responseMessage);
        Optional<User> newUserOpt = userRepository.findById(Long.valueOf(newUsername));
        Assertions.assertTrue(newUserOpt.isPresent());

        // clean up
        newUserOpt.ifPresent(user -> userRepository.delete(user));
    }
}