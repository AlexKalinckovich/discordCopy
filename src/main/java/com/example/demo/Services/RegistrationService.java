package com.example.demo.Services;

import com.example.demo.Models.RegisterClasses.RegisterRequest;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
public class RegistrationService {

    private final RegisterRequest registerRequest;
    private final UserRepository userRepository;

    public RegistrationService(RegisterRequest registerRequest, UserRepository userRepository) {
        this.registerRequest = registerRequest;
        this.userRepository = userRepository;
    }

    @Transactional
    public void registerUser(final User user, final Model model) {
        if(registerRequest.isAlreadyRegistered()){
            model.addAttribute("error", "Пользователь с данным именем уже зарегестрирован");
        }else{
            userRepository.saveUser(user.getUsername(), user.getPassword());
        }
    }

}
