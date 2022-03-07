package csaba79coder.webspringcaptcha.service;

import csaba79coder.webspringcaptcha.dto.RegisterUserRequest;
import csaba79coder.webspringcaptcha.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getUserByID(Long ID);
    String register(RegisterUserRequest user);
}
