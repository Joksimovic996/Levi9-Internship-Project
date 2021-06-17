package com.levi9.internship.TennisScheduler.serviceImpl;

import com.levi9.internship.TennisScheduler.exceptions.TennisException;
import com.levi9.internship.TennisScheduler.model.TennisPlayer;
import com.levi9.internship.TennisScheduler.modelDTO.AuthenticationRequestDTO;
import com.levi9.internship.TennisScheduler.modelDTO.TokenStateDTO;
import com.levi9.internship.TennisScheduler.security.TokenUtils;
import com.levi9.internship.TennisScheduler.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthenticationServiceImpl  implements AuthenticationService {
    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;

    public AuthenticationServiceImpl(TokenUtils tokenUtils, AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public TokenStateDTO createAuthenticationToken(AuthenticationRequestDTO requestDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        TennisPlayer tennisPlayer = (TennisPlayer) authentication.getPrincipal();

        return new TokenStateDTO(tokenUtils.generateToken(tennisPlayer.getUsername()), tokenUtils.getExpiredIn());
    }

    @Override
    public TokenStateDTO refreshAuthenticationToken(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        System.out.println(token);
        String username = tokenUtils.getUsernameFromToken(token);
        System.out.println(username);
        TennisPlayer tennisPlayer = (TennisPlayer) userDetailsService.loadUserByUsername(username);

        if (tokenUtils.canTokenBeRefreshed(token, tennisPlayer.getLastPasswordResetDate())) {
            return new TokenStateDTO(tokenUtils.refreshToken(token), tokenUtils.getExpiredIn());
        } else {
            throw new TennisException(HttpStatus.BAD_REQUEST, "Token cannot be refreshed!");
        }
    }

}
