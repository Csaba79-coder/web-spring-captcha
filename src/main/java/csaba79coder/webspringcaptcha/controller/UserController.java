package csaba79coder.webspringcaptcha.controller;

import csaba79coder.webspringcaptcha.dto.RegisterUserRequest;
import csaba79coder.webspringcaptcha.model.User;
import csaba79coder.webspringcaptcha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /*GetMapping("/user")
    public Object getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAll();
    }

    /*@GetMapping("/users/{username}")
    public User getUserByUserName(@PathVariable String name) {

        //return userService.getUserByID(ID);
    }*/

    /*@PostMapping("/register")
    public String register(@RequestBody RegisterUserRequest user) {
        return userService.register(user);
    }*/
}


