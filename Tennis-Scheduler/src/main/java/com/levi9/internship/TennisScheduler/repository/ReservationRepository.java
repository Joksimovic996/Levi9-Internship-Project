package com.levi9.internship.TennisScheduler.repository;

import com.levi9.internship.TennisScheduler.model.Reservation;
import com.levi9.internship.TennisScheduler.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query("select r from Reservation r where (r.id = :reservationId and r.deleted = false)")
    Reservation getReservationById(@Param("reservationId") Long id);

    @Query("select r from Reservation r where (r.deleted = false)")
    List<Reservation> getAllReservations();
}
