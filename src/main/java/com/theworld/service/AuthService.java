package com.theworld.service;

import com.theworld.dtos.DoctorDTO;
import com.theworld.dtos.PatientDTO;
import com.theworld.dtos.SignupRequest;
import com.theworld.dtos.UserDTO;

public interface AuthService {

    UserDTO createUser(SignupRequest signupRequest);


}
