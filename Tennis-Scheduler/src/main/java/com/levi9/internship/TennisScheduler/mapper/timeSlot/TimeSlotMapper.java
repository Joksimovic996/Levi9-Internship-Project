package com.levi9.internship.TennisScheduler.mapper.timeSlot;

import com.levi9.internship.TennisScheduler.mapper.reservation.ReservationMapper;
import com.levi9.internship.TennisScheduler.mapper.tennisCourt.TennisCourtMapper;
import com.levi9.internship.TennisScheduler.model.TimeSlot;
import com.levi9.internship.TennisScheduler.modelDTO.timeSlot.TimeSlotDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { TennisCourtMapper.class, ReservationMapper.class })
public interface TimeSlotMapper {
    TimeSlot map(TimeSlotDTO timeSlotDTO);
    TimeSlotDTO map(TimeSlot timeSlot);
}
