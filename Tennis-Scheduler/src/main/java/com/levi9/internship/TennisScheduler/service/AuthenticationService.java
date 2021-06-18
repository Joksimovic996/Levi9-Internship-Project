package com.levi9.internship.TennisScheduler.service;

import com.levi9.internship.TennisScheduler.modelDTO.AuthenticationRequestDTO;
import com.levi9.internship.TennisScheduler.modelDTO.TokenStateDTO;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    TokenStateDTO createAuthenticationToken(AuthenticationRequestDTO requestDTO);

}
