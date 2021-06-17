package com.levi9.internship.TennisScheduler.modelDTO.tennisCourt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateTennisCourtDTO {
    @NotNull(message = "Tennis court name cannot be null!")
    private String name;
    @NotNull(message = "Court type cannot be null, choose some of options!")
    private String courtType;
    @NotNull(message = "Price per minute cannot be null!")
    private Double pricePerMinute;
}
