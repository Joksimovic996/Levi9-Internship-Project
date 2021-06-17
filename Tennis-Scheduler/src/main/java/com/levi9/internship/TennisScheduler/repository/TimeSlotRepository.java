package com.levi9.internship.TennisScheduler.repository;

import com.levi9.internship.TennisScheduler.model.TennisPlayer;
import com.levi9.internship.TennisScheduler.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {


    @Query("select s from TimeSlot s where(s.reservation.id = :reservationId and s.deleted = false and s.reservation.deleted=false)")
    List<TimeSlot> getTimeSlotsOfReservation(Long reservationId);

    @Query("select s from TimeSlot s where (s.startDateAndTime between :startDate and :endDate and s.tennisCourt.id = :courtId and s.deleted=false)")
    List<TimeSlot> getTimeSlotOfSameDateAndCourt(LocalDateTime startDate, LocalDateTime endDate, Long courtId);

    @Query("select s from TimeSlot s inner join TennisPlayer tp on (s.reservation.tennisPlayer.id = tp.id) where (s.startDateAndTime between :startDate and :endDate and tp.id = :playerId and s.deleted=false) ")
    List<TimeSlot> getTimeSlotsOfTennisPlayerForGivenDate(Long playerId, LocalDateTime startDate, LocalDateTime endDate );

    @Query("select t from TimeSlot t where (t.id = :timeSlotId and t.deleted = false)")
    TimeSlot getTimeSlotById(@Param("timeSlotId") Long id);

    @Query("select ts from TimeSlot ts where (ts.deleted = false)")
    List<TimeSlot> getAllSlots();

}
