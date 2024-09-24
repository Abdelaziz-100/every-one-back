package com.theworld.model;

import com.theworld.dtos.SignupRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstname;
    private String lastname;
    private String email;
    @Column(name = "phone_number", nullable = true)
    private String phonenumber;

    public void setGender(SignupRequest.Gender gender) {
    }

    public enum Gender {
        MALE, FEMALE;
    }

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;
    private String password;
}

