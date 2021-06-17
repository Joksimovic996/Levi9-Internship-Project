package com.levi9.internship.TennisScheduler.controller;

import com.levi9.internship.TennisScheduler.modelDTO.AuthenticationRequestDTO;
import com.levi9.internship.TennisScheduler.modelDTO.TokenStateDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer.CreateTennisPlayerDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer.TennisPlayerDTO;
import com.levi9.internship.TennisScheduler.service.AuthenticationService;
import com.levi9.internship.TennisScheduler.service.TennisPlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(value = "Authentication Endpoints")
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final TennisPlayerService tennisPlayerService;

    public AuthenticationController(AuthenticationService authenticationService, TennisPlayerService tennisPlayerService) {
        this.authenticationService = authenticationService;
        this.tennisPlayerService = tennisPlayerService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenStateDTO> createAuthenticationToken(@RequestBody AuthenticationRequestDTO requestDTO) {
        return ResponseEntity.ok(authenticationService.createAuthenticationToken(requestDTO));
    }

    @PostMapping("/refresh")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PLAYER')")
    public ResponseEntity<TokenStateDTO> refreshAuthenticationToken(@RequestBody HttpServletRequest request) {
        return ResponseEntity.ok(authenticationService.refreshAuthenticationToken(request));
    }

    @PostMapping("/signup")
    @ApiOperation(
            value = "Sign up a New Tennis Player",
            notes = "Requires an instance of CreateTennisPlayerDTO"
    )
    public ResponseEntity<TennisPlayerDTO> addTennisPlayer(
            @RequestBody CreateTennisPlayerDTO tennisPlayerDTO) {
        return ResponseEntity.ok(tennisPlayerService.addTennisPlayer(tennisPlayerDTO, "ROLE_PLAYER"));
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(
            value = "Sign up a New Admin",
            notes = "Requires an instance of CreateTennisPlayerDTO"
    )
    public ResponseEntity<TennisPlayerDTO> addAdmin(@RequestBody CreateTennisPlayerDTO adminDTO) {
        return ResponseEntity.ok(tennisPlayerService.addTennisPlayer(adminDTO, "ROLE_ADMIN"));

    }

    @PostMapping("/giveMeAccountBack")
    public ResponseEntity<TennisPlayerDTO> giveMeBackMyAccount(@RequestParam String email) {
        return ResponseEntity.ok(tennisPlayerService.giveMeBackMyAccount(email));
    }
}
