package com.theworld.controller;


import com.theworld.dtos.SignupRequest;
import com.theworld.dtos.UserDTO;
import com.theworld.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SingUpUserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest) {
        UserDTO createUser = authService.createUser(signupRequest);
         if (createUser == null) {
             return new ResponseEntity<>("User is not created, try again.", HttpStatus.BAD_REQUEST);
         } else {
             return new ResponseEntity<>(createUser, HttpStatus.CREATED);
         }

    }
}
