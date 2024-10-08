package com.theworld.service;

import com.theworld.dtos.SignupRequest;
import com.theworld.dtos.UserDTO;
import com.theworld.model.Doctor;
import com.theworld.model.Patient;
import com.theworld.model.User;
import com.theworld.model.enums.Role;
import com.theworld.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin("http://localhost:4200")
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignupRequest signupRequest) {
        // Check the user type and create corresponding user

        User createdUser;

        switch (signupRequest.role) {
            case ADMIN:
                createdUser = createAdmin(signupRequest);
                break;
            case DOCTOR:
                createdUser = createDoctor(signupRequest);
                break;
            default:
                createdUser = createPatient(signupRequest);
                break;
        }

        // Create and return UserDTO from the created user
        return mapToUserDTO(createdUser);
    }

    private User createAdmin(SignupRequest signupRequest) {
        User user = new User();
        user.setFirstname(signupRequest.getFirstname());
        user.setLastname(signupRequest.getLastname());
        user.setEmail(signupRequest.getEmail());
        user.setPhonenumber(signupRequest.getPhonenumber());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(Role.ADMIN);

        return userRepository.save(user);
    }

    private Patient createPatient(SignupRequest signupRequest) {
        Patient patient = new Patient();
        patient.setFirstname(signupRequest.getFirstname());
        patient.setLastname(signupRequest.getLastname());
        patient.setEmail(signupRequest.getEmail());
        patient.setPhonenumber(signupRequest.getPhonenumber());
        patient.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        patient.setRole(Role.PATIENT);
        patient.setDateOfBirth(signupRequest.getDateOfBirth());
        // Set additional patient-specific fields here (e.g., dateOfBirth)

        return userRepository.save(patient);
    }

    private Doctor createDoctor(SignupRequest signupRequest) {
        Doctor doctor = new Doctor();
        doctor.setFirstname(signupRequest.getFirstname());
        doctor.setLastname(signupRequest.getLastname());
        doctor.setEmail(signupRequest.getEmail());
        doctor.setPhonenumber(signupRequest.getPhonenumber());
        doctor.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        doctor.setRole(Role.DOCTOR);
        doctor.setSpeciality(signupRequest.getSpecialty());
        // Set additional doctor-specific fields here (e.g., specialty)

        return userRepository.save(doctor);
    }

    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhonenumber(user.getPhonenumber());
        userDTO.setPassword(user.getPassword()); // Optionally exclude password

        return userDTO;
    }
}
