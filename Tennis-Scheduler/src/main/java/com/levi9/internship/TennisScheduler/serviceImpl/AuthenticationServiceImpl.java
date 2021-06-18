package com.levi9.internship.TennisScheduler.serviceImpl;

import com.levi9.internship.TennisScheduler.model.TennisPlayer;
import com.levi9.internship.TennisScheduler.modelDTO.AuthenticationRequestDTO;
import com.levi9.internship.TennisScheduler.modelDTO.TokenStateDTO;
import com.levi9.internship.TennisScheduler.security.TokenUtils;
import com.levi9.internship.TennisScheduler.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl  implements AuthenticationService {
    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(TokenUtils tokenUtils, AuthenticationManager authenticationManager) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public TokenStateDTO createAuthenticationToken(AuthenticationRequestDTO requestDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        TennisPlayer tennisPlayer = (TennisPlayer) authentication.getPrincipal();

        return new TokenStateDTO(tokenUtils.generateToken(tennisPlayer.getUsername()), tokenUtils.getExpiredIn());
    }


}
