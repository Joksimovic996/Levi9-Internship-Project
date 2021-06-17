package com.levi9.internship.TennisScheduler.serviceImpl;

import com.levi9.internship.TennisScheduler.exceptions.TennisException;
import com.levi9.internship.TennisScheduler.mapper.tennisCourt.CreateTennisCourtMapper;
import com.levi9.internship.TennisScheduler.mapper.tennisCourt.TennisCourtMapper;
import com.levi9.internship.TennisScheduler.mapper.tennisCourt.UpdateTennisCourtMapper;
import com.levi9.internship.TennisScheduler.mapper.timeSlot.TimeSlotMapper;
import com.levi9.internship.TennisScheduler.model.TennisCourt;
import com.levi9.internship.TennisScheduler.modelDTO.tennisCourt.CreateTennisCourtDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisCourt.TennisCourtDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisCourt.UpdateTennisCourtDTO;
import com.levi9.internship.TennisScheduler.modelDTO.timeSlot.TimeSlotDTO;
import com.levi9.internship.TennisScheduler.repository.TennisCourtRepository;
import com.levi9.internship.TennisScheduler.service.TennisCourtService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TennisCourtServiceImpl implements TennisCourtService {

    private final TennisCourtRepository courtRepository;
    private final TennisCourtMapper tennisCourtMapper;
    private final CreateTennisCourtMapper createTennisCourtMapper;
    private final UpdateTennisCourtMapper updateTennisCourtMapper;
    private final TimeSlotMapper timeSlotMapper;



    public TennisCourtServiceImpl(TennisCourtRepository courtRepository, TennisCourtMapper tennisCourtMapper, CreateTennisCourtMapper createTennisCourtMapper, UpdateTennisCourtMapper updateTennisCourtMapper, TimeSlotMapper timeSlotMapper) {
        this.courtRepository = courtRepository;
        this.tennisCourtMapper = tennisCourtMapper;
        this.createTennisCourtMapper = createTennisCourtMapper;
        this.updateTennisCourtMapper = updateTennisCourtMapper;
        this.timeSlotMapper = timeSlotMapper;
    }

    @Override
    public void addTennisCourt(CreateTennisCourtDTO tennisCourtDTO) {
        TennisCourt newCourt = createTennisCourtMapper.map(tennisCourtDTO);
        newCourt.setDeleted(false);
        courtRepository.save(newCourt);
    }

    @Override
    public TennisCourtDTO getTennisCourtById(Long id) {
        return tennisCourtMapper.map(courtRepository.getTennisCourtById(id));
    }

    @Override
    public TennisCourtDTO getTennisCourtByName(String name) {
        TennisCourt tennisCourt = courtRepository.getTennisCourtByName(name);
        if(tennisCourt == null)
            throw new TennisException(HttpStatus.NOT_FOUND, "Tennis Court with that name does not exist!");
        return tennisCourtMapper.map(tennisCourt);
    }

    @Override
    public List<TennisCourtDTO> getAllCourts() {
        List<TennisCourtDTO> tennisCourts = new ArrayList<>();
        courtRepository.getAllCourts().forEach(tennisCourt -> tennisCourts.add(tennisCourtMapper.map(tennisCourt)));
        return tennisCourts;
    }

    @Override
    public void updateTennisCourt(UpdateTennisCourtDTO tennisCourtDTO, Long id) {
        TennisCourt temp = courtRepository.getTennisCourtById(id);
        TennisCourt updated = updateTennisCourtMapper.map(tennisCourtDTO);
        updated.setId(temp.getId());
        updated.setName(temp.getName());
        courtRepository.save(updated);

    }

    @Override
    public void deleteTennisCourt(Long id) {
        TennisCourt tennisCourt = courtRepository.getTennisCourtById(id);
        tennisCourt.setDeleted(true);
        courtRepository.save(tennisCourt);
    }

    @Override
    public List<TimeSlotDTO> getTimeSlotsByTennisCourt(Long id) {
        List<TimeSlotDTO> timeSlots = new ArrayList<>();
        courtRepository.getTimeSlotsOfTennisCourt(id).forEach(timeSlot -> timeSlots.add(timeSlotMapper.map(timeSlot)));
        return timeSlots;
    }
}
