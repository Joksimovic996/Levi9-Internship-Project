package com.levi9.internship.TennisScheduler.modelDTO.timeSlot;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ApiModel(description = "Create time slot DTO details")
public class CreateTimeSlotDTO {
    @FutureOrPresent
    @NotNull
    @ApiModelProperty(notes = "When you want to start playing")
    private LocalDateTime startDateAndTime;
    @FutureOrPresent
    @NotNull
    @ApiModelProperty(notes = "When you are done with playing")
    private LocalDateTime endDateAndTime;
    @NotNull
    @ApiModelProperty(notes = "On which court you want to play (ID of court)")
    private Long tennisCourt;
}
