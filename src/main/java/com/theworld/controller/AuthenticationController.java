package com.theworld.controller;

import com.theworld.dtos.*;
import com.theworld.service.AuthService;
import com.theworld.service.jwt.UserDetailsServiceImpl;
import com.theworld.service.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AuthenticationController {


    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest) {
        System.out.println("Sign-up endpoint hit");

        UserDTO createdUser = authService.createUser(signupRequest);

        if (createdUser == null) {
            return new ResponseEntity<>("User is not created, try again.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }
    }

    @PostMapping("/sign-in")
    public AuthenticationResponse createAuthenticationToken(
            @RequestBody AuthenticationDTO authenticationDTO,
            HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {

        try {
            // Authenticate user
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(), authenticationDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password!");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
            return null;
        }

        // Load the user details to get their role
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getEmail());

        // Generate the JWT
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        String role = userDetails.getAuthorities().stream()
                .findFirst()  // Assuming user has only one role
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_USER");  // Default to ROLE_USER if no role is found

        // Return JWT and role in the response
        return new AuthenticationResponse(jwt, role);
    }

}
