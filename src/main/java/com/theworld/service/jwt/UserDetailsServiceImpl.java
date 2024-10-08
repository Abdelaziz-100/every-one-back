package com.theworld.service.jwt;

import com.theworld.model.User;
import com.theworld.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch the user from the DB
        User user = userRepository.findFirstByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Convert the user's role to GrantedAuthority
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());

        // Return the UserDetails object with email, password, and authorities
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(authority)  // Set the user's authorities (role)
        );
    }
}
