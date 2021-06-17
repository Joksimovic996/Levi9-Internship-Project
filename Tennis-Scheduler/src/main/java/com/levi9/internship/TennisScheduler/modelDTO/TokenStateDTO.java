package com.levi9.internship.TennisScheduler.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenStateDTO {
    private String accessToken;
    private int expiresIn;
}
