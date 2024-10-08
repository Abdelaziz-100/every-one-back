package com.theworld.model;

import com.theworld.model.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "patients")
public class Patient extends User {

    private Date dateOfBirth;

    public Patient() {
        super();
        this.setRole(Role.PATIENT); // Assign role explicitly in constructor
    }

}
