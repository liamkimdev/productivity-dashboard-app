package org.productivity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class SecurityConfig {

    private final JwtConverter converter;
//    private final AuthenticationConfiguration authConfig;
//    private final AuthenticationConfiguration oauth2ClientRegistrationRepository;
    private final UserDetailsService userDetailsService;

//    public SecurityConfig(JwtConverter converter, AuthenticationConfiguration authConfig, AuthenticationConfiguration oauth2ClientRegistrationRepository){
//        this.converter = converter;
//        this.oauth2ClientRegistrationRepository = oauth2ClientRegistrationRepository;
//        this.authConfig = authConfig;
//    }

    public SecurityConfig(JwtConverter converter, UserDetailsService userDetailsService) {
        this.converter = converter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authConfig) throws Exception {
        http.csrf().disable();

        http.cors();

        http.authorizeRequests()
                // user
                .antMatchers( "/user/authenticate").permitAll()
                .antMatchers("/user/create_account").permitAll()
                .antMatchers("/user/refresh_token").authenticated()
                .antMatchers(HttpMethod.GET,
                        "/user/*").hasAnyAuthority("USER", "ADMIN")

                // dashboard
                .antMatchers(HttpMethod.GET,
                        "/dashboard/{dashboardId}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET,
                        "/dashboard/user/{userId}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/dashboard").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/dashboard/*").hasAnyAuthority("USER", "ADMIN")


                // note
                .antMatchers(HttpMethod.GET,
                        "/dashboard/noteWidget/note", "/dashboard/noteWidget/note/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/dashboard/noteWidget/note").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/dashboard/noteWidget/note/*").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/dashboard/noteWidget/note/*").hasAnyAuthority("USER", "ADMIN")

                .antMatchers("/**").denyAll()
                .and()
                .addFilter(new JwtRequestFilter(authenticationManager(authConfig), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }


    // filter chain without -> lambda
//        @Bean
//        public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authConfig) throws Exception {
//            http.cors();
//            http.csrf().ignoringAntMatchers("/api/**").and()
//                    .authorizeRequests()
//                    .antMatchers("/", "/error").permitAll()
//                    .antMatchers(HttpMethod.GET, "/").permitAll()
//                    .antMatchers("/authenticate").permitAll()
//                    .antMatchers("/refresh_token").authenticated()
//                    .antMatchers("/create_account").permitAll()
//                    .antMatchers(HttpMethod.GET, "/dashboard", "/dashboard/*").permitAll()
//                    .antMatchers(HttpMethod.POST, "/dashboard").hasAnyAuthority("USER", "ADMIN")
//                    .antMatchers(HttpMethod.PUT, "/dashboard/*").hasAnyAuthority("USER", "ADMIN")
//                    .antMatchers(HttpMethod.DELETE, "/dashboard/*").hasAnyAuthority("ADMIN")
//                    .antMatchers("/**").denyAll()
//                    .and()
//                    .exceptionHandling()
//                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                    .and()
//                    .oauth2Login()
//                    .defaultSuccessUrl("/", true);
//
//            http.addFilter(new JwtRequestFilter(authenticationManager(authConfig), converter));
//
//            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//            return http.build();
//        }

        // filter chain with -> lambda expression
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authConfig) throws Exception {
//
//        http.cors();
//
//        http.csrf().ignoringAntMatchers("/api/**").and()
//
//
//            .authorizeRequests(a -> a
//                    .antMatchers("/", "/error").permitAll()
////                    .anyRequest().authenticated()
//
//                    // standard sign-in
//                    .antMatchers(HttpMethod.GET,
//                            "/").permitAll()
//                    // Security . . .
//                    .antMatchers("/authenticate").permitAll()
//                    .antMatchers("/refresh_token").authenticated()
//
//                    //Widgets
//                    .antMatchers("/create_account").permitAll()
//                    .antMatchers("/refresh_token").authenticated()
//                    .antMatchers(HttpMethod.GET, "/dashboard", "/dashboard/*").permitAll()
//                    .antMatchers(HttpMethod.POST, "/dashboard").hasAnyAuthority("USER", "ADMIN")
//                    .antMatchers(HttpMethod.PUT, "/dashboard/*").hasAnyAuthority("USER", "ADMIN")
//                    .antMatchers(HttpMethod.DELETE, "/dashboard/*").hasAnyAuthority("ADMIN")
//
//                    // add ant matchers for note widget
//
//                    // add ant matchers for note
//
//                    // if we get to this point, let's deny all requests
//                    .antMatchers("/**").denyAll()
//                    .and()
//
//            )
//            .exceptionHandling(e -> e
//                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//            )
//            .oauth2Login()
//            .defaultSuccessUrl("/", true);
//
//        http.addFilter(new JwtRequestFilter(authenticationManager(authConfig), converter));
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        return http.build();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}