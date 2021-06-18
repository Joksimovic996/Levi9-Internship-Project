package com.levi9.internship.TennisScheduler.modelDTO.reservation;


import com.levi9.internship.TennisScheduler.modelDTO.timeSlot.CreateTimeSlotDTO;
import com.levi9.internship.TennisScheduler.validation.ValidCreateTimeSlotDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Create reservation DTO details")
public class CreateReservationDTO {

    @NotNull(message = "Reservation must have payment type!")
    @ApiModelProperty(notes = "Right now, you can choose between two options: PAY_WITH_CREDIT_CARD or PAY_WITH_CASH")
    private String paymentType;
    @ApiModelProperty(notes = "List of time slots you want to reserve")
    @ValidCreateTimeSlotDTO
    private Set<CreateTimeSlotDTO> timeSlots;
}
