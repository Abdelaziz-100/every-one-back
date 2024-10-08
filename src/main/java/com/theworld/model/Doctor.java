package com.theworld.model;

import com.theworld.model.enums.DoctorEnums;
import com.theworld.model.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "doctors")
public class Doctor extends User {

    private DoctorEnums.Speciality speciality;

    public Doctor() {
        super();
        this.setRole(Role.DOCTOR); // Assign role explicitly in constructor
    }

}
