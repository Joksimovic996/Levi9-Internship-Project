package com.levi9.internship.TennisScheduler.mapper.reservation;

import com.levi9.internship.TennisScheduler.model.Reservation;
import com.levi9.internship.TennisScheduler.modelDTO.reservation.CreateReservationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateReservationMapper {
    CreateReservationDTO map(Reservation reservation);
    Reservation map(CreateReservationDTO createReservationDTO);
}
