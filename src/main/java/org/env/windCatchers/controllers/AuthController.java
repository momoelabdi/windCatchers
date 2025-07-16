package org.env.windCatchers.controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;

@RestController

public class AuthController {
    
    @GetMapping("/login")
    public void login(HttpServletResponse response) throws IOException{
        response.sendRedirect("/oauth2/authorization/backend-api");

    }

    @GetMapping("/login/success")
    public Map<String, Object> loginSuccess(@AuthenticationPrincipal OAuth2User principal){
        return Map.of("user_info", principal.getAttributes());
    }
}
