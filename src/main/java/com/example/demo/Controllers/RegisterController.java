package com.example.demo.Controllers;

import com.example.demo.Services.RegistrationService;
import com.example.demo.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    private final RegistrationService registrationService;

    public RegisterController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @ModelAttribute User user,
                           Model model) {
        String result = "redirect:/authorization";
        user.setPassword(password);
        user.setUsername(username);
        registrationService.registerUser(user,model);
        if (model.containsAttribute("error")) {
            result = "register";// если есть ошибка, остаемся на странице регистрации
        }

        return result;
    }

}
