package com.levi9.internship.TennisScheduler.modelDTO.reservation;

import com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer.TennisPlayerDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Details about the reservation")
public class ReservationDTO {
    @ApiModelProperty(notes = "The unique ID of the reservation")
    private Long id;
    @ApiModelProperty(notes = "The tennis player who reserved it")
    private TennisPlayerDTO tennisPlayer;
    @ApiModelProperty(notes = "The date of the reservation")
    private LocalDateTime reservationDate;
    @ApiModelProperty(notes = "The selected payment type")
    private String paymentType;
    @ApiModelProperty(notes = "The price that tennis player needs to pay for reservation")
    private Double price;
    @ApiModelProperty(notes = "Indication of payment status")
    private Boolean paid;
}
