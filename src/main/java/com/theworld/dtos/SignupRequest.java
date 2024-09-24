package com.theworld.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class SignupRequest {


    private String firstname;

    private String lastname;


    private String phonenumber;

    private String email;


    public enum Gender {
        MALE, FEMALE;
    }

    @Enumerated(EnumType.STRING)
    private Gender gender;


    private int age;


    private String password;
}
