package com.example.stupek.person;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PersonDto {
    private Long id;

    @NotNull
    private String login;

    private char @NotNull [] password;

    @NotNull
    @Email
    private String email;

    @Min(0)
    private Float balance;

    private PersonRole personRole;

    private PersonStatus personStatus;

}
