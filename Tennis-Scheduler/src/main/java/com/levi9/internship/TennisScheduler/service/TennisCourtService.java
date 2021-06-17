package com.levi9.internship.TennisScheduler.service;

import com.levi9.internship.TennisScheduler.modelDTO.tennisCourt.CreateTennisCourtDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisCourt.TennisCourtDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisCourt.UpdateTennisCourtDTO;
import com.levi9.internship.TennisScheduler.modelDTO.timeSlot.TimeSlotDTO;

import java.util.List;

public interface TennisCourtService {

    void addTennisCourt(CreateTennisCourtDTO tennisCourtDTO);
    TennisCourtDTO getTennisCourtById(Long id);
    TennisCourtDTO getTennisCourtByName(String name);
    List<TennisCourtDTO> getAllCourts();
    void updateTennisCourt(UpdateTennisCourtDTO tennisCourtDTO, Long id);
    void deleteTennisCourt(Long id);
    List<TimeSlotDTO> getTimeSlotsByTennisCourt(Long id);


}
