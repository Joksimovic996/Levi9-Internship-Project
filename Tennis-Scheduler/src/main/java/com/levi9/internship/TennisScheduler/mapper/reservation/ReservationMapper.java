package com.levi9.internship.TennisScheduler.mapper.reservation;

import com.levi9.internship.TennisScheduler.mapper.tennisPlayer.TennisPlayerMapper;
import com.levi9.internship.TennisScheduler.model.Reservation;
import com.levi9.internship.TennisScheduler.modelDTO.reservation.ReservationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TennisPlayerMapper.class)
public interface ReservationMapper {
    Reservation map(ReservationDTO reservationDTO);
    ReservationDTO map(Reservation reservation);
}
