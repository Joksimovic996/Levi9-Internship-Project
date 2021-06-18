package com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Create tennis player DTO details")
public class CreateTennisPlayerDTO {

    @ApiModelProperty(notes = "First name of tennis player")
    private String name;
    @ApiModelProperty(notes = "Last name of tennis player")
    private String lastName;
    @Email(message = "Tennis player's email must be valid!")
    @NotNull(message = "Tennis player's email cannot be null!")
    @ApiModelProperty(notes = "Email of tennis player")
    private String email;
    @NotNull(message = "Tennis player's username cannot be null!")
    @ApiModelProperty(notes = "Username of tennis player")
    private String username;
    @NotNull(message = "Tennis player's password cannot be null!")
    @ApiModelProperty(notes = "Password of tennis player")
    private String password;
    @ApiModelProperty(notes = "Date of birth of the tennis player")
    private LocalDateTime dateOfBirth;

}