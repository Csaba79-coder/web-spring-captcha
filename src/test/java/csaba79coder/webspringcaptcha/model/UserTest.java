package csaba79coder.webspringcaptcha.model;

import csaba79coder.webspringcaptcha.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void createUserTest() {
        assertNotNull(userRepository.findAll().get(0));
        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    void createNewUserTest() {
        User user = new User();
        user.setUsername("Csaba");
        user.setEmail("csabavadasz79@gmail.com");
        user.setRegistrationDate(Timestamp.from(Instant.now()));
        user.setPassword("1234");
        user.setRole(Role.ROLE_ADMIN);
        user.setEnable(true);
        assertNotNull(userRepository.findAll());
        userRepository.save(user);
        assertEquals(2, userRepository.findAll().size());
    }
}