package com.levi9.internship.TennisScheduler.controller;

import com.levi9.internship.TennisScheduler.modelDTO.AuthenticationRequestDTO;
import com.levi9.internship.TennisScheduler.modelDTO.TokenStateDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer.CreateTennisPlayerDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer.TennisPlayerDTO;
import com.levi9.internship.TennisScheduler.service.AuthenticationService;
import com.levi9.internship.TennisScheduler.service.TennisPlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "List of Authentication Endpoints")
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final TennisPlayerService tennisPlayerService;

    public AuthenticationController(AuthenticationService authenticationService, TennisPlayerService tennisPlayerService) {
        this.authenticationService = authenticationService;
        this.tennisPlayerService = tennisPlayerService;
    }

    @PostMapping("/login")
    @ApiOperation(
            value = "Log in tennis player",
            notes = "Provide an authentication request to log in tennis player",
            response = TennisPlayerDTO.class
    )
    public ResponseEntity<TokenStateDTO> createAuthenticationToken(
            @ApiParam(
                    value = "Authentication request for the player you want to log in",
                    required = true
            )
            @RequestBody AuthenticationRequestDTO requestDTO) {
        return ResponseEntity.ok(authenticationService.createAuthenticationToken(requestDTO));
    }


    @PostMapping("/signup")
    @ApiOperation(
            value = "Sign up a New Tennis Player",
            notes = "Requires an instance of CreateTennisPlayerDTO"
    )
    public ResponseEntity<TennisPlayerDTO> addTennisPlayer(
            @ApiParam(
                    value = "Create tennis player DTO instance to sign up",
                    required = true
            )
            @Valid @RequestBody CreateTennisPlayerDTO tennisPlayerDTO) {
        return ResponseEntity.ok(tennisPlayerService.addTennisPlayer(tennisPlayerDTO, "ROLE_PLAYER"));
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(
            value = "Sign up a New Admin",
            notes = "Requires an instance of CreateTennisPlayerDTO"
    )
    public ResponseEntity<TennisPlayerDTO> addAdmin(
            @ApiParam(
                    value = "Create tennis player admin DTO instance to sign up",
                    required = true
            )
            @Valid @RequestBody CreateTennisPlayerDTO adminDTO) {
        return ResponseEntity.ok(tennisPlayerService.addTennisPlayer(adminDTO, "ROLE_ADMIN"));

    }

    @PostMapping("/giveMeAccountBack")
    @ApiOperation(
            value = "Activate account of tennis player",
            notes = "Requires an email of player you want to activate back"
    )
    public ResponseEntity<TennisPlayerDTO> giveMeBackMyAccount(@RequestParam String email) {
        return ResponseEntity.ok(tennisPlayerService.giveMeBackMyAccount(email));
    }
}
