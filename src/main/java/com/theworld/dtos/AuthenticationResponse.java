package com.theworld.dtos;

import com.theworld.model.enums.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    public String jwt;
    public String role ;
}
