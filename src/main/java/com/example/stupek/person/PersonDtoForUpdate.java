package com.example.stupek.person;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class PersonDtoForUpdate {
    @Email
    private String email;

    private PersonRole personRole;
}
