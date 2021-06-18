package com.levi9.internship.TennisScheduler.serviceImpl;


import com.levi9.internship.TennisScheduler.exceptions.TennisException;
import com.levi9.internship.TennisScheduler.mapper.tennisPlayer.CreateTennisPlayerMapper;
import com.levi9.internship.TennisScheduler.mapper.tennisPlayer.TennisPlayerMapper;
import com.levi9.internship.TennisScheduler.model.Authority;
import com.levi9.internship.TennisScheduler.model.TennisPlayer;
import com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer.CreateTennisPlayerDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer.TennisPlayerDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer.UpdateTennisPlayerDTO;
import com.levi9.internship.TennisScheduler.repository.TennisPlayerRepository;
import com.levi9.internship.TennisScheduler.service.AuthorityService;
import com.levi9.internship.TennisScheduler.service.TennisPlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TennisPlayerServiceImpl implements TennisPlayerService {


    private final TennisPlayerRepository tennisPlayerRepository;
    private final TennisPlayerMapper tennisPlayerMapper;
    private final CreateTennisPlayerMapper createTennisPlayerMapper;
    private final AuthorityService authorityService;
    private final PasswordEncoder passwordEncoder;


    public TennisPlayerServiceImpl(TennisPlayerRepository tennisPlayerRepository, TennisPlayerMapper tennisPlayerMapper, CreateTennisPlayerMapper createTennisPlayerMapper, AuthorityService authorityService, PasswordEncoder passwordEncoder)
    {
        this.tennisPlayerRepository=tennisPlayerRepository;
        this.tennisPlayerMapper = tennisPlayerMapper;
        this.createTennisPlayerMapper = createTennisPlayerMapper;
        this.authorityService = authorityService;
        this.passwordEncoder = passwordEncoder;

    }


    @Override
    public TennisPlayerDTO getTennisPlayer(Long id) {
        TennisPlayer tennisPlayer = tennisPlayerRepository.getTennisPlayerById(id);
        if (tennisPlayer != null) {
            return tennisPlayerMapper.map(tennisPlayer);
        }else
            throw new TennisException(HttpStatus.NOT_FOUND, "Tennis player does not exist!");
    }

    @Override
    public List<TennisPlayerDTO> getAllPlayers() {
        List<TennisPlayer> tennisPlayers=tennisPlayerRepository.getAllPlayers();
        List<TennisPlayerDTO> tennisPlayerDTOS=new ArrayList<>();
            if (!tennisPlayers.isEmpty()) {
                for (TennisPlayer temp : tennisPlayers) {
                    tennisPlayerDTOS.add(tennisPlayerMapper.map(temp));
                }
            }
        return tennisPlayerDTOS;
    }

    @Override
    public TennisPlayerDTO addTennisPlayer(CreateTennisPlayerDTO tennisPlayerDTO, String role) {
        TennisPlayer tennisPlayer = createTennisPlayerMapper.map(tennisPlayerDTO);
        List<String> emails = tennisPlayerRepository.checkAvailableEmail(tennisPlayer.getEmail());
        if (emails.contains(tennisPlayer.getEmail())) {
            throw new TennisException(HttpStatus.BAD_REQUEST, "Try with another email address!");
        }
        List<Authority> authorities = authorityService.findByName(role);
        tennisPlayer.setAuthorities(authorities);
        tennisPlayer.setPassword(passwordEncoder.encode(tennisPlayerDTO.getPassword()));
        tennisPlayer.setDeleted(false);
        tennisPlayer = tennisPlayerRepository.save(tennisPlayer);
        return tennisPlayerMapper.map(tennisPlayer);
    }

    @Override
    public void updateTennisPlayer(UpdateTennisPlayerDTO updateTennisPlayerDTO, Long id) {
        TennisPlayer updatedTennisPlayer=tennisPlayerRepository.getTennisPlayerById(id);
        if(updatedTennisPlayer==null)
            throw new TennisException(HttpStatus.NOT_FOUND,"Tennis player does not exist");
        TennisPlayer tennisPlayer = tennisPlayerRepository.getTennisPlayerByEmail(updateTennisPlayerDTO.getEmail());
        if(tennisPlayer != null && tennisPlayer.getId()!=id ) {
            throw new TennisException(HttpStatus.BAD_REQUEST,"Tennis Player with that email already exist!");
        }
        updatedTennisPlayer.setName(updateTennisPlayerDTO.getName());
        updatedTennisPlayer.setLastName(updateTennisPlayerDTO.getLastName());
        updatedTennisPlayer.setDateOfBirth(updateTennisPlayerDTO.getDateOfBirth());
        updatedTennisPlayer.setEmail(updateTennisPlayerDTO.getEmail());
        tennisPlayerRepository.save(updatedTennisPlayer);
    }

    @Override
    public void deleteTennisPlayer(Long id) {

        TennisPlayer tennisPlayer = tennisPlayerRepository.getTennisPlayerById(id);
        tennisPlayer.setDeleted(true);
        tennisPlayerRepository.save(tennisPlayer);
    }

    @Override
    public TennisPlayerDTO getTennisPlayerByEmail(String email) {
        TennisPlayer tennisPlayer = tennisPlayerRepository.getTennisPlayerByEmail(email);
        if (tennisPlayer == null)
            throw new TennisException(HttpStatus.NOT_FOUND, "Tennis Player with that email does not exist!");
        return tennisPlayerMapper.map(tennisPlayer);

    }

    @Override
    public TennisPlayerDTO restoreAccount(String email) {
        TennisPlayer tennisPlayer = tennisPlayerRepository.restoreAccount(email);
        if (tennisPlayer != null) {
            tennisPlayer.setDeleted(false);
            tennisPlayerRepository.save(tennisPlayer);
            return tennisPlayerMapper.map(tennisPlayer);
        }else
            throw new TennisException(HttpStatus.BAD_REQUEST, "Incorrect email or your account is active!");
    }


}
