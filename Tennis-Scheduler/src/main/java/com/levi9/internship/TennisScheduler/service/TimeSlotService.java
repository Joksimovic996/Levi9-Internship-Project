package com.levi9.internship.TennisScheduler.service;

import com.levi9.internship.TennisScheduler.modelDTO.timeSlot.TimeSlotDTO;

import java.util.List;

public interface TimeSlotService {

    TimeSlotDTO getTimeSlot(Long id);
    List<TimeSlotDTO> getAllTimeSlots();

}
