package com.levi9.internship.TennisScheduler.controller;


import com.levi9.internship.TennisScheduler.modelDTO.timeSlot.TimeSlotDTO;
import com.levi9.internship.TennisScheduler.serviceImpl.TimeSlotServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "List of Time Slot Endpoints")
@RequestMapping("/tennis/time-slot")
public class TimeSlotController {

    private final TimeSlotServiceImpl timeSlotService;

    public TimeSlotController(TimeSlotServiceImpl timeSlotService){
        this.timeSlotService = timeSlotService;
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Finds Reservation by ID",
            notes = "Provide an ID to look up specific reservation",
            response = TimeSlotDTO.class
    )
    public ResponseEntity<TimeSlotDTO> getTimeSlot(
            @ApiParam(
                    value = "ID value for the time slot you want to find",
                    required = true
            )@PathVariable Long id){
        return ResponseEntity.ok(timeSlotService.getTimeSlot(id));
    }

    @GetMapping
    @ApiOperation(
            value = "Finds a list of all Time Slots in the database",
            response = List.class
    )
    public ResponseEntity<List<TimeSlotDTO>> getTimeSlots(){
        return ResponseEntity.ok(timeSlotService.getAllTimeSlots());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Logical delete of time slot",
            notes = "Requires an ID of Time Slot"
    )
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> deleteTimeSlot(@PathVariable Long id){
        timeSlotService.deleteTimeSlot(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/time-slots")
    @ApiOperation(
            value = "Returns all time slots of selected reservation",
            notes = "Requires an ID of Reservation"
    )
    public ResponseEntity<List<TimeSlotDTO>> getTimeSlotsOfReservation(Long id){
        return ResponseEntity.ok(timeSlotService.getTimeSlotsOfReservation(id));
    }

}
