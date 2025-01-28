package com.example.demo.Controllers;

import com.example.demo.Services.AuthorizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping("/authorization")
    public String authorization() {
        authorizationService.setAuthorized(false);
        return "authorization";
    }

    @PostMapping("/authorization")
    public String postAuthorization(@RequestParam String username,
                                    @RequestParam String password,
                                    Model model) {
        boolean isAuthorized = authorizationService.authorize(username,password,model);
        return "redirect:/" + (isAuthorized ? "mainPage" : "authorization");
    }

}
