package com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(description = "Details about the tennis player")
public class TennisPlayerDTO {
        @ApiModelProperty(notes = "The unique ID of the tennis player")
        private Long id;
        @ApiModelProperty(notes = "The tennis player's name")
        private String name;
        @ApiModelProperty(notes = "The tennis player's last name")
        private String lastName;
        @ApiModelProperty(notes = "The unique email address of tennis player")
        private String email;
        @ApiModelProperty(notes = "The tennis player's date of birth")
        private LocalDateTime dateOfBirth;

}
