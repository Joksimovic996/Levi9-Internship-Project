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
public class UpdateTennisPlayerDTO {

    @ApiModelProperty(notes = "The tennis player's name")
    private String name;
    @ApiModelProperty(notes = "The tennis player's last name")
    private String lastName;
    @ApiModelProperty(notes = "The unique email address of tennis player")
    @Email(message = "Tennis player's email must be valid!")
    @NotNull(message = "Tennis player's email cannot be null!")
    private String email;
    @ApiModelProperty(notes = "The tennis player's date of birth")
    private LocalDateTime dateOfBirth;

}
