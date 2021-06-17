package com.levi9.internship.TennisScheduler.controller;

import com.levi9.internship.TennisScheduler.modelDTO.creditcard.CreditCardDTO;
import com.levi9.internship.TennisScheduler.modelDTO.reservation.CreateReservationDTO;
import com.levi9.internship.TennisScheduler.modelDTO.reservation.ReservationDTO;
import com.levi9.internship.TennisScheduler.serviceImpl.ReservationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "List of Reservation Endpoints")
@RequestMapping("/tennis/reservation")
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    public ReservationController(ReservationServiceImpl reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Finds Reservation by ID",
            notes = "Provide an ID to look up specific reservation",
            response = ReservationDTO.class
    )
    public ResponseEntity<ReservationDTO> getReservation(
            @ApiParam(
                    value = "ID value for the reservation you want to find",
                    required = true
            )
            @PathVariable Long id){
        return ResponseEntity.ok(reservationService.getReservation(id));
    }

    @GetMapping
    @ApiOperation(
            value = "Finds a list of all Reservations in the database",
            response = List.class
    )
    public ResponseEntity<List<ReservationDTO>> getReservations(){
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PostMapping
    @ApiOperation(
            value = "Adds a new Reservation",
            notes = "Requires an instance of ReservationDTO and an ID of Tennis player who reserved it"
    )
    public ResponseEntity<HttpStatus> addReservation(
            @ApiParam(
                    value = "ID value for the the tennis player who reserved it",
                    required = true
            )
            @RequestParam(name = "tennisPlayerId") Long tennisPlayerId,
            @ApiParam(
                    value = "CreateReservationDTO",
                    required = true
            )
            @Valid @RequestBody CreateReservationDTO reservation,
            CreditCardDTO creditCardDTO){
        reservationService.addReservation(reservation, tennisPlayerId, creditCardDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);


    }

    @PutMapping("/{id}")
    @ApiOperation(
            value = "Updates the existing Reservation",
            notes = "Requires an instance of CreateReservationDTO and ID of the tennis player"
    )
    public ResponseEntity<HttpStatus> updateReservation(
            @ApiParam(
                    value = "Boolean",
                    required = true
            )@RequestParam Boolean paid,
            @ApiParam(
                    value = "ID value of reservation",
                    required = true
            )
            @PathVariable Long id){
        reservationService.updateReservation(paid, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Deletes the existing Reservation",
            notes = "Requires an ID of the reservation"
    )
    public ResponseEntity<HttpStatus> deleteReservation(
            @ApiParam(
                    value = "ID of the Reservation",
                    required = true
            )@PathVariable Long id){
        reservationService.deleteReservationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
