package com.levi9.internship.TennisScheduler.repository;

import com.levi9.internship.TennisScheduler.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {


    @Query("select s from TimeSlot s where(s.reservation.id = :reservationId)")
    List<TimeSlot> getTimeSlotsOfReservation(Long reservationId);

    @Query("select s from TimeSlot s where (s.startDateAndTime between :startDate and :endDate and s.tennisCourt.id = :courtId)")
    List<TimeSlot> getTimeSlotOfSameDateAndCourt(LocalDateTime startDate, LocalDateTime endDate, Long courtId);

    @Query("select s from TimeSlot s inner join TennisPlayer tp on (s.reservation.tennisPlayer.id = tp.id) where (s.startDateAndTime between :startDate and :endDate and tp.id = :playerId) ")
    List<TimeSlot> getTimeSlotsOfTennisPlayerForGivenDate(Long playerId, LocalDateTime startDate, LocalDateTime endDate );

}
