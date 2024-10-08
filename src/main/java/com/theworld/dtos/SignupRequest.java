package com.theworld.dtos;


import com.theworld.model.enums.DoctorEnums;
import com.theworld.model.enums.Role;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SignupRequest {


    private String firstname;

    private String lastname;

    private String phonenumber;

    private String email;

    private String password;
    public Role role ;
    @Nullable
    DoctorEnums.Speciality specialty;
    @Nullable
    private Date dateOfBirth;

}
