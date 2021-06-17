package com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateTennisPlayerDTO {

    private String name;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private LocalDateTime dateOfBirth;

}