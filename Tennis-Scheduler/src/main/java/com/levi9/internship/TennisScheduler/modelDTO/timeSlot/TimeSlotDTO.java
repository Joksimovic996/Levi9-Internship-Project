package com.levi9.internship.TennisScheduler.modelDTO.timeSlot;

import com.levi9.internship.TennisScheduler.modelDTO.reservation.ReservationDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisCourt.TennisCourtDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ApiModel(description = "Details about the time slot")
public class TimeSlotDTO {
    @ApiModelProperty(notes = "The unique ID of the time slot")
    private Long id;
    @ApiModelProperty(notes = "The start date and time of the current time slot")
    private LocalDateTime startDateAndTime;
    @ApiModelProperty(notes = "The end date and time of the current time slot")
    private LocalDateTime endDateAndTime;
    @ApiModelProperty(notes = "The tennis court that has current time slot")
    private TennisCourtDTO tennisCourt;
    @ApiModelProperty(notes = "The reservation that has current time slot")
    private ReservationDTO reservation;
}
