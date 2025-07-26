package org.env.windCatchers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestCustomizers;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private final LoginSuccessHandler loginSuccessHandler;
        private final LogoutHandler logoutSuccessHandler;

        public SecurityConfig(
                        LoginSuccessHandler loginSuccessHandler,
                        LogoutHandler logoutSuccessHandler) {
                this.loginSuccessHandler = loginSuccessHandler;
                this.logoutSuccessHandler = logoutSuccessHandler;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http,
                        OAuth2AuthorizationRequestResolver pkceResolver) throws Exception {
                return http

                                .authorizeHttpRequests(auth -> {
                                        auth.requestMatchers("/auth/**", "/", "/error", "/webjars/**").permitAll();
                                        auth.anyRequest().authenticated();
                                })
                                .oauth2Login(oauth -> oauth
                                                .authorizationEndpoint(
                                                                authorization -> authorization
                                                                                .authorizationRequestResolver(
                                                                                                pkceResolver))
                                                .successHandler(loginSuccessHandler))
                                .logout(logout -> logout
                                                .logoutSuccessHandler(logoutSuccessHandler)
                                                .invalidateHttpSession(true)
                                                .deleteCookies("*"))
                                .csrf(csrf -> csrf.disable()) // ¿?¿
                                .formLogin(form -> form.disable())
                                .build();
        }

        @Bean // setup PKCE auth flow with request resolver
        public OAuth2AuthorizationRequestResolver pkceResolver(
                        ClientRegistrationRepository clientRegistrationRepository) {
                DefaultOAuth2AuthorizationRequestResolver resolver = new DefaultOAuth2AuthorizationRequestResolver(
                                clientRegistrationRepository,
                                OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI);

                resolver.setAuthorizationRequestCustomizer(OAuth2AuthorizationRequestCustomizers.withPkce());
                return resolver;
        }
}