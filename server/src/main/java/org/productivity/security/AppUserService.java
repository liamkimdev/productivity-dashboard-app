package org.productivity.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    // We will store our users in memory for now so that we can focus
    // on authentication. Later we will get our users from the database.
    private List<UserDetails> users;

    public AppUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

        makeUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Find a matching user
        UserDetails userDetails = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);

        // Returning `null` is an interface violation,
        // if the username is not found a `UsernameNotFoundException` must be thrown.
        if (userDetails == null) {
            throw new UsernameNotFoundException(username + " not found.");
        }

        return userDetails;
    }

    private void makeUsers() {

        // Leverage Spring Security's `User` class to build some in-memory users.
        // This is not a permanent solution. Eventually, we'll want to
        // store our users and roles in the database.

        UserDetails user = User.builder()
                .passwordEncoder(passwordEncoder::encode)
                .username("user")
                .password("user-password")
                .authorities("USER")
                .build();
        UserDetails admin = User.builder()
                .passwordEncoder(passwordEncoder::encode)
                .username("admin")
                .password("admin-password")
                .authorities("ADMIN")
                .build();

        users = List.of(user, admin);
    }
}
