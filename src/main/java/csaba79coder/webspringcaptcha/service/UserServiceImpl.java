package csaba79coder.webspringcaptcha.service;

import csaba79coder.webspringcaptcha.dto.RegisterUserRequest;
import csaba79coder.webspringcaptcha.model.User;
import csaba79coder.webspringcaptcha.repository.UserAuthorityRepository;
import csaba79coder.webspringcaptcha.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserAuthorityRepository userAuthorityRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserAuthorityRepository userAuthorityRepository) {
        this.userRepository = userRepository;
        this.userAuthorityRepository = userAuthorityRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByID(Long ID) {
        return userRepository.getById(ID);
    }

    @Override
    public String register(RegisterUserRequest user) {
        return null;
    }
}
