package com.example.demo.Models.RegisterClasses;

import com.example.demo.Repositories.UserRepository;
import com.example.demo.Models.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Optional;

@Component
@SessionScope
public class RegisterChecker {

    private final UserRepository userRepository;

    public RegisterChecker(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean checkUserExists(String username) {
        Optional<User> user = findUserByUsername(username);
        return user.isPresent();
    }
}
