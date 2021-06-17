package com.levi9.internship.TennisScheduler.modelDTO.reservation;


import com.levi9.internship.TennisScheduler.modelDTO.timeSlot.CreateTimeSlotDTO;
import com.levi9.internship.TennisScheduler.validation.ValidCreateTimeSlotDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CreateReservationDTO {

    @NotNull(message = "Reservation must have payment type!")
    private String paymentType;
    @ValidCreateTimeSlotDTO
    private Set<CreateTimeSlotDTO> timeSlots;
}
