package com.levi9.internship.TennisScheduler.serviceImpl;

import com.levi9.internship.TennisScheduler.enumerations.PaymentType;
import com.levi9.internship.TennisScheduler.exceptions.TennisException;
import com.levi9.internship.TennisScheduler.mapper.reservation.CreateReservationMapper;
import com.levi9.internship.TennisScheduler.mapper.reservation.ReservationMapper;
import com.levi9.internship.TennisScheduler.mapper.timeSlot.CreateTimeSlotMapper;
import com.levi9.internship.TennisScheduler.model.Reservation;
import com.levi9.internship.TennisScheduler.model.TennisCourt;
import com.levi9.internship.TennisScheduler.model.TimeSlot;
import com.levi9.internship.TennisScheduler.modelDTO.creditcard.CreditCardDTO;
import com.levi9.internship.TennisScheduler.modelDTO.reservation.CreateReservationDTO;
import com.levi9.internship.TennisScheduler.modelDTO.reservation.ReservationDTO;
import com.levi9.internship.TennisScheduler.modelDTO.timeSlot.CreateTimeSlotDTO;
import com.levi9.internship.TennisScheduler.repository.ReservationRepository;
import com.levi9.internship.TennisScheduler.repository.TennisCourtRepository;
import com.levi9.internship.TennisScheduler.repository.TennisPlayerRepository;
import com.levi9.internship.TennisScheduler.repository.TimeSlotRepository;
import com.levi9.internship.TennisScheduler.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final CreateReservationMapper createReservationMapper;
    private final TennisPlayerRepository tennisPlayerRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final CreateTimeSlotMapper timeSlotMapper;
    private final TennisCourtRepository tennisCourtRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper, CreateReservationMapper createReservationMapper, TennisPlayerRepository tennisPlayerRepository, TimeSlotRepository timeSlotRepository, CreateTimeSlotMapper timeSlotMapper, TennisCourtRepository tennisCourtRepository){
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.createReservationMapper = createReservationMapper;
        this.tennisPlayerRepository = tennisPlayerRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.timeSlotMapper = timeSlotMapper;
        this.tennisCourtRepository = tennisCourtRepository;
    }

    @Override
    public ReservationDTO getReservation(Long id) {
        return reservationMapper.map(reservationRepository.getById(id));
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> tempReservations = reservationRepository.findAll();
        List<ReservationDTO> reservations = new ArrayList<>();
        for (Reservation temp : tempReservations) {
            reservations.add(reservationMapper.map(temp));
        }
        return reservations;

    }

    @Override
    public void addReservation(CreateReservationDTO reservation, Long tennisPlayerId, CreditCardDTO creditCardDTO) {
        var newReservation = createReservationMapper.map(reservation);

        if (reservation.getPaymentType().equalsIgnoreCase(PaymentType.PAY_WITH_CREDIT_CARD.name())) {
            if (creditCardDTO.getCreditCardNumber() == null || creditCardDTO.getCreditCardValidDate() == null || creditCardDTO.getCreditCardCvcCode() == 0)
                throw new TennisException(HttpStatus.BAD_REQUEST, "Please enter credit card information!");
            else
                newReservation.setPaid(true);
                // call payment service
        }

        List<TimeSlot> slotsToBeSaved = new ArrayList<>();
        List<TimeSlot> slotsInBase;
        var price = 0.0;
        var tennisPlayer = tennisPlayerRepository.getById(tennisPlayerId);

        List<TimeSlot> alreadyHasTimeSlotsOnThatDay;

        for(CreateTimeSlotDTO timeSlotDTO : reservation.getTimeSlots()) {

            var startDate = timeSlotDTO.getStartDateAndTime().toLocalDate();
            var startDateMidnight = LocalDateTime.of(startDate, LocalTime.MIDNIGHT);
            var dayAfterMidnight = startDateMidnight.plusDays(1).toLocalDate().atStartOfDay();

            alreadyHasTimeSlotsOnThatDay = timeSlotRepository.getTimeSlotsOfTennisPlayerForGivenDate(tennisPlayerId, startDateMidnight, dayAfterMidnight);
            if(!alreadyHasTimeSlotsOnThatDay.isEmpty())
                throw new TennisException(HttpStatus.BAD_REQUEST, "You cannot reserve two timeslots same day!");


            slotsInBase = timeSlotRepository.getTimeSlotOfSameDateAndCourt(timeSlotDTO.getStartDateAndTime(), timeSlotDTO.getEndDateAndTime(), timeSlotDTO.getTennisCourt());
            if (slotsInBase.isEmpty()){
                var tennisCourt = tennisCourtRepository.getById(timeSlotDTO.getTennisCourt());
                var timeSlot = setCurrentTimeSlot(timeSlotDTO, newReservation, tennisCourt);
                slotsToBeSaved.add(timeSlot);
                price += getPriceOfTimeSlot(timeSlot);
            } else {
                throw new TennisException(HttpStatus.BAD_REQUEST, "You cannot have overlapping with timeslots!");
            }


        }
        //additional fee for more than five slots
        price = slotsToBeSaved.size() > 5 ? price + 10 : price;

        newReservation.setReservationDate(LocalDateTime.now());
        newReservation.setPrice(price);
        newReservation.setTennisPlayer(tennisPlayer);
        if (newReservation.getPaymentType().equals(PaymentType.PAY_WITH_CASH)) {
            newReservation.setPaid(false);
        } else {
            newReservation.setPaid(true);
        }
        reservationRepository.save(newReservation);
        for(TimeSlot temp : slotsToBeSaved) {
            timeSlotRepository.save(temp);
        }
    }

    @Override
    public void updateReservation(Boolean paid, Long id) {
        var reservation = reservationRepository.getById(id);
        if (reservation.getPaymentType().equals(PaymentType.PAY_WITH_CASH)) {
            reservation.setPaid(paid);
            reservationRepository.save(reservation);
        } else
            throw new TennisException(HttpStatus.BAD_REQUEST, "You cannot change paid value if payment type is 'pay with credit card'!");

    }

    @Override
    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }

    protected Double getPriceOfTimeSlot(TimeSlot timeSlot) {
        return ((timeSlot.getEndDateAndTime().getHour() * 60 + timeSlot.getEndDateAndTime().getMinute())
                - (timeSlot.getStartDateAndTime().getHour() * 60 + timeSlot.getStartDateAndTime().getMinute())) *
                timeSlot.getTennisCourt().getPricePerMinute();
    }

    private TimeSlot setCurrentTimeSlot(CreateTimeSlotDTO createTimeSlotDTO, Reservation newReservation, TennisCourt tennisCourt) {
        var timeSlot = timeSlotMapper.map(createTimeSlotDTO);
        timeSlot.setReservation(newReservation);
        timeSlot.setTennisCourt(tennisCourt);
        timeSlot.setDeleted(false);
        return timeSlot;
    }
}
