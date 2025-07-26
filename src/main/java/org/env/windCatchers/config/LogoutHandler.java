package org.env.windCatchers.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LogoutHandler implements LogoutSuccessHandler {

    @Value("${app.frontend.origin}")
    private String frontendOrigin;

    private final OidcClientInitiatedLogoutSuccessHandler oidcLogoutHandler;

    public LogoutHandler(ClientRegistrationRepository clientRegistrationRepository,
            @Value("${app.frontend.origin}") String frontendOrigin) {
        this.oidcLogoutHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        // set the redirect uri to the FE after logout
        this.oidcLogoutHandler.setPostLogoutRedirectUri(frontendOrigin);
    }

    // handle user logout on the IDP side using the ( post_logout_redirect_uri )
    // and redirect back to the frontend
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        oidcLogoutHandler.onLogoutSuccess(request, response, authentication);
    }

    
}