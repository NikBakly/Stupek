package com.example.stupek.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDto {

    @NotNull
    private String login;

    @NotNull
    private char[] password;

    @NotNull
    @Email
    private String email;

    @Min(0)
    private Float balance;

    private PersonRole personRole;

    private PersonStatus personStatus;

}
