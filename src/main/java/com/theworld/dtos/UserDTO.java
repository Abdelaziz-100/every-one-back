package com.theworld.dtos;



import com.theworld.model.User;

import com.theworld.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number", nullable = true)
    private String phonenumber;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Role role;
}
