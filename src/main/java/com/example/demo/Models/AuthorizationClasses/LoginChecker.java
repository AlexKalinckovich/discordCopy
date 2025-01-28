package com.example.demo.Models.AuthorizationClasses;

import com.example.demo.Repositories.UserRepository;
import com.example.demo.Models.User;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Optional;

@Component
@SessionScope
public class LoginChecker {

    @Getter
    private Optional<User> user = Optional.empty();

    private final UserRepository userRepository;

    public LoginChecker(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean checkUserExists(String username) {
        user = findUserByUsername(username);
        return user.isPresent();
    }

    public boolean checkLogin(String username, String password) {
        user = findUserByUsername(username);
        boolean isAuthorized = false;
        if (user.isPresent()) {
            String userPassword = user.get().getPassword();
            isAuthorized = password.equals(userPassword);
        }
        return isAuthorized;
    }

}
