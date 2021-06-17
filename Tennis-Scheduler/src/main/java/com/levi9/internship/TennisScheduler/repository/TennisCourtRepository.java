package com.levi9.internship.TennisScheduler.repository;

import com.levi9.internship.TennisScheduler.model.TennisCourt;
import com.levi9.internship.TennisScheduler.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TennisCourtRepository extends JpaRepository<TennisCourt, Long> {


    @Query("select t from TennisCourt t where (t.id = :courtId and t.deleted = false)")
    TennisCourt getTennisCourtById(@Param("courtId") Long id);

    @Query("select t from TennisCourt t where (t.deleted = false)")
    List<TennisCourt> getAllCourts();

    @Query("select t from TennisCourt  t where (lower(t.name)  = lower(:courtName) and t.deleted = false)")
    TennisCourt getTennisCourtByName(@Param("courtName") String name);

    @Query("select s from TimeSlot s where(s.tennisCourt.id = :courtId and s.deleted = false)")
    List<TimeSlot> getTimeSlotsOfTennisCourt(@Param("courtId") Long id);
}
