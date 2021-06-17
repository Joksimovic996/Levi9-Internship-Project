package com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateTennisPlayerDTO {

    private String name;
    private String lastName;
    @Email(message = "Tennis player's email must be valid!")
    @NotNull(message = "Tennis player's email cannot be null!")
    private String email;
    @NotNull(message = "Tennis player's username cannot be null!")
    private String username;
    @NotNull(message = "Tennis player's password cannot be null!")
    private String password;
    private LocalDateTime dateOfBirth;

}