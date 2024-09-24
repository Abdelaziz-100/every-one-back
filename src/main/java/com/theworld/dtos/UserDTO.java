package com.theworld.dtos;



import com.theworld.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class UserDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;

    public void setGender(User.Gender gender) {
    }

    private enum Gender {
        MALE, FEMALE;
    }
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;
    private String password;
}
