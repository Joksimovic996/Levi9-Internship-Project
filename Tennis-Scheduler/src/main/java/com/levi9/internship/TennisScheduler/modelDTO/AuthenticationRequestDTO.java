package com.levi9.internship.TennisScheduler.modelDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Authentication details")
public class AuthenticationRequestDTO {
    @ApiModelProperty(notes = "Authentication username")
    private String username;
    @ApiModelProperty(notes = "Authentication password")
    private String password;
}
