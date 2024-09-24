package com.theworld.service;

import com.theworld.dtos.SignupRequest;
import com.theworld.dtos.UserDTO;
import com.theworld.model.User;
import com.theworld.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDTO createUser(SignupRequest signupRequest) {
       User user = new User();
       user.setFirstname(signupRequest.getFirstname());
       user.setLastname(signupRequest.getLastname());
       user.setEmail(signupRequest.getEmail());
       user.setPhonenumber(signupRequest.getPhonenumber());
       user.setGender(signupRequest.getGender());
       user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
       User createdUser = userRepository.save(user);
       UserDTO userDTO = new UserDTO();
       userDTO.setFirstname(createdUser.getFirstname());
       userDTO.setLastname(createdUser.getLastname());
       userDTO.setEmail(createdUser.getEmail());
       userDTO.setPhonenumber(createdUser.getPhonenumber());
       userDTO.setGender(createdUser.getGender());
       userDTO.setPassword(createdUser.getPassword());

        return userDTO;
    }
}
