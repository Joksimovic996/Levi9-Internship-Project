package com.levi9.internship.TennisScheduler.serviceImpl;

import com.levi9.internship.TennisScheduler.enumerations.PaymentType;
import com.levi9.internship.TennisScheduler.exceptions.TennisException;
import com.levi9.internship.TennisScheduler.mapper.timeSlot.TimeSlotMapper;
import com.levi9.internship.TennisScheduler.model.Reservation;
import com.levi9.internship.TennisScheduler.model.TennisPlayer;
import com.levi9.internship.TennisScheduler.model.TimeSlot;
import com.levi9.internship.TennisScheduler.modelDTO.timeSlot.TimeSlotDTO;
import com.levi9.internship.TennisScheduler.repository.ReservationRepository;
import com.levi9.internship.TennisScheduler.repository.TimeSlotRepository;
import com.levi9.internship.TennisScheduler.service.TimeSlotService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotMapper timeSlotMapper;
    private final ReservationRepository reservationRepository;
    private final ReservationServiceImpl reservationService;


    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository, TimeSlotMapper timeSlotMapper, ReservationRepository reservationRepository, ReservationServiceImpl reservationService) {
        this.timeSlotRepository = timeSlotRepository;
        this.timeSlotMapper = timeSlotMapper;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    @Override
    public TimeSlotDTO getTimeSlot(Long id) {
        TimeSlot timeSlot = timeSlotRepository.getTimeSlotById(id);
        if (timeSlot != null) {
            return timeSlotMapper.map(timeSlot);
        }else
            throw new TennisException(HttpStatus.NOT_FOUND, "Time slot does not exist!");
    }

    @Override
    public List<TimeSlotDTO> getAllTimeSlots() {
        List<TimeSlotDTO> timeSlots = new ArrayList<>();
        timeSlotRepository.getAllSlots().forEach(timeSlot -> timeSlots.add(timeSlotMapper.map(timeSlot)));
        return timeSlots;

    }

    @Override
    public void deleteTimeSlot(Long id) {
        TimeSlot newTimeSlot = timeSlotRepository.getTimeSlotById(id);
        Reservation reservation = reservationRepository.getReservationById(newTimeSlot.getReservation().getId());
        List<TimeSlot> timeSlots = timeSlotRepository.getTimeSlotsOfReservation(newTimeSlot.getReservation().getId());

        if(reservation.getPaymentType().equals(PaymentType.PAY_WITH_CREDIT_CARD)){
            throw new TennisException(HttpStatus.BAD_REQUEST, "You cannot delete time slot of Reservation that is paid with credit card!");
        }

        if(timeSlots.size() == 1){
           reservation.setDeleted(true);
           reservationRepository.save(reservation);
        } else {
            reservation.setPrice(reservation.getPrice() - reservationService.getPriceOfTimeSlot(newTimeSlot));
            reservationRepository.save(reservation);
            if(timeSlots.size() == 6)
                reservation.setPrice(reservation.getPrice() - 10);
        }


        newTimeSlot.setDeleted(true);
        timeSlotRepository.save(newTimeSlot);
    }

    @Override
    public List<TimeSlotDTO> getTimeSlotsOfReservation(Long id) {
        List<TimeSlotDTO> timeSlots = new ArrayList<>();
        timeSlotRepository.getTimeSlotsOfReservation(id).forEach(timeSlot -> {
            System.out.println(timeSlot.getId());
            timeSlots.add(timeSlotMapper.map(timeSlot));
        });
        return timeSlots;
    }

}
