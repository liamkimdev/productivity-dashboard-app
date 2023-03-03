package org.productivity.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTypeFilter extends OncePerRequestFilter {

    private final AuthenticationConfiguration authConfig;
    private final ClientRegistrationRepository oauth2ClientRegistrationRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationTypeFilter(AuthenticationConfiguration authConfig, ClientRegistrationRepository oauth2ClientRegistrationRepository, AuthenticationManager authenticationManager) {
        this.authConfig = authConfig;
        this.oauth2ClientRegistrationRepository = oauth2ClientRegistrationRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authType = request.getParameter("authType");

        if (authType != null && authType.equalsIgnoreCase("oauth2")) {
            OAuth2LoginAuthenticationFilter oauthFilter = new OAuth2LoginAuthenticationFilter(oauth2ClientRegistrationRepository, (OAuth2AuthorizedClientService) authenticationManager);
            oauthFilter.setAuthorizationRequestRepository(new HttpSessionOAuth2AuthorizationRequestRepository());
            oauthFilter.setAuthenticationSuccessHandler((request1, response1, authentication) -> {
                // Handle successful authentication here
            });
            oauthFilter.setAuthenticationFailureHandler((request1, response1, exception) -> {
                // Handle failed authentication here
            });
            oauthFilter.setFilterProcessesUrl("/authenticate");
            oauthFilter.setAllowSessionCreation(true);
            oauthFilter.doFilter(request, response, filterChain);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
