package com.levi9.internship.TennisScheduler.service;

import com.levi9.internship.TennisScheduler.modelDTO.creditcard.CreditCardDTO;
import com.levi9.internship.TennisScheduler.modelDTO.reservation.CreateReservationDTO;
import com.levi9.internship.TennisScheduler.modelDTO.reservation.ReservationDTO;

import java.util.List;

public interface ReservationService {

    ReservationDTO getReservation(Long id);
    List<ReservationDTO> getAllReservations();
    void addReservation(CreateReservationDTO reservation, Long tennisPlayerId, CreditCardDTO creditCardDTO);
    void updateReservation(Boolean paid,Long id);
    void deleteReservationById(Long id);
}
