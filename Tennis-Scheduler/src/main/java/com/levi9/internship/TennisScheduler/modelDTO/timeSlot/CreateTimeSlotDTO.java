package com.levi9.internship.TennisScheduler.modelDTO.timeSlot;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CreateTimeSlotDTO {
    @FutureOrPresent
    @NotNull
    private LocalDateTime startDateAndTime;
    @FutureOrPresent
    @NotNull
    private LocalDateTime endDateAndTime;
    @NotNull
    private Long tennisCourt;
}
