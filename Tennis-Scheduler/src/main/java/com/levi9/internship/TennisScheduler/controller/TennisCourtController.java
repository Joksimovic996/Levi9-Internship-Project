package com.levi9.internship.TennisScheduler.controller;

import com.levi9.internship.TennisScheduler.modelDTO.tennisCourt.CreateTennisCourtDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisCourt.TennisCourtDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisCourt.UpdateTennisCourtDTO;
import com.levi9.internship.TennisScheduler.modelDTO.timeSlot.TimeSlotDTO;
import com.levi9.internship.TennisScheduler.serviceImpl.TennisCourtServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "List Of Tennis Court Endpoints")
@RequestMapping("/tennis/tennis-court")
public class TennisCourtController {

    private final TennisCourtServiceImpl courtService;

    public TennisCourtController(TennisCourtServiceImpl courtService) {
        this.courtService = courtService;
    }

    // Get Tennis Court By ID
    @GetMapping("/getById")
    @ApiOperation(
            value = "Finds Tennis Court By ID",
            notes = "Provide an ID to look up specific tennis court",
            response = TennisCourtDTO.class
    )
    public ResponseEntity<TennisCourtDTO> getTennisCourtById(
            @ApiParam(
                    value = "ID value for the court you need to retrieve",
                    required = true
            )
            @RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(courtService.getTennisCourtById(id));
    }


    // Get Tennis Court By Name
    @GetMapping("/getByName")
    @ApiOperation(
            value = "Finds Tennis Court By Name",
            notes = "Provide name of court to look up specific tennis court",
            response = TennisCourtDTO.class
    )
    public ResponseEntity<TennisCourtDTO> getTennisCourtByName(
            @ApiParam(
                    value = "Name value for the court you need to retrieve",
                    required = true
            )
            @RequestParam(value = "name") String name) {
        return ResponseEntity.ok(courtService.getTennisCourtByName(name));
    }

    // Get Time Slots Of Tennis Court
    @GetMapping("/{id}/time-slots")
    @ApiOperation(
            value = "Finds a List of Time Slots of Tennis Court",
            response = List.class
    )
    public ResponseEntity<List<TimeSlotDTO>> getTimeSlotsOfTennisCourt(
            @ApiParam(
                    value = "ID of Tennis Court",
                    required = true
            )
            @PathVariable Long id) {
        return ResponseEntity.ok(courtService.getTimeSlotsByTennisCourt(id));
    }

    // Get All Tennis Courts
    @GetMapping()
    @ApiOperation(
            value = "Finds a List Of All Tennis Courts In The System",
            response = List.class
    )
    public ResponseEntity<List<TennisCourtDTO>> getTennisCourts() {
        return ResponseEntity.ok(courtService.getAllCourts());
    }

    //Add New Tennis Court
    @PostMapping()
    @ApiOperation(
            value = "Adds a New Tennis Court",
            notes = "Requires an instance of CreateTennisCourtDTO"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TennisCourtDTO> addTennisCourt(
            @ApiParam(
                    value = "Instance of CreateTennisCourtDTO",
                    required = true
            )
            @Valid @RequestBody CreateTennisCourtDTO tennisCourtDTO) {
        courtService.addTennisCourt(tennisCourtDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Update Tennis Court
    @PutMapping("/{id}")
    @ApiOperation(
            value = "Updates The Existing Tennis Court",
            notes = "Requires an instance of CreateTennisCourtDTO and ID of the tennis court"
    )
    public ResponseEntity<TennisCourtDTO> updateTennisCourt(@RequestBody UpdateTennisCourtDTO tennisCourtDTO, @PathVariable Long id) {
        courtService.updateTennisCourt(tennisCourtDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete Tennis Court
    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Deletes The Existing Tennis Court",
            notes = "Requires an ID of the tennis court"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TennisCourtDTO> deleteTennisCourt(
            @ApiParam(
                    value = "ID of the Tennis Court",
                    required = true
            )
            @PathVariable Long id) {
        courtService.deleteTennisCourt(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
