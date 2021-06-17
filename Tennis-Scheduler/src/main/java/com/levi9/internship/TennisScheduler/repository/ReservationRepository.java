package com.levi9.internship.TennisScheduler.repository;

import com.levi9.internship.TennisScheduler.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {



}
