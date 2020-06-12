package com.sidecar.pizza.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * This is a mock class for demonstration purpose
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     *
     * Just a mock for demonstration here.
     * username: sidecar / password: pizza
     *
     * In real application, it can be maintained in database.
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = passwordEncoder.encode("pizza");
        if ("sidecar".equals(username)) {
            return new User("sidecar", password, new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}
