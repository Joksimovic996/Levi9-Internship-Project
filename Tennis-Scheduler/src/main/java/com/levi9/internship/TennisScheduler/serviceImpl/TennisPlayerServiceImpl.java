package com.levi9.internship.TennisScheduler.serviceImpl;


import com.levi9.internship.TennisScheduler.exceptions.TennisException;
import com.levi9.internship.TennisScheduler.mapper.tennisPlayer.CreateTennisPlayerMapper;
import com.levi9.internship.TennisScheduler.mapper.tennisPlayer.TennisPlayerMapper;
import com.levi9.internship.TennisScheduler.model.Authority;
import com.levi9.internship.TennisScheduler.model.TennisPlayer;
import com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer.CreateTennisPlayerDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer.TennisPlayerDTO;
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
        return tennisPlayerMapper.map(tennisPlayerRepository.getTennisPlayerById(id));
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
    public TennisPlayerDTO addTennisPlayer(CreateTennisPlayerDTO tennisPlayerDTO) {
        TennisPlayer tennisPlayer = createTennisPlayerMapper.map(tennisPlayerDTO);
        List<Authority> authorities = authorityService.findByName("ROLE_PLAYER");
        tennisPlayer.setAuthorities(authorities);
        tennisPlayer.setPassword(passwordEncoder.encode(tennisPlayerDTO.getPassword()));
        tennisPlayer.setDeleted(false);
        tennisPlayer = tennisPlayerRepository.save(tennisPlayer);
        return tennisPlayerMapper.map(tennisPlayer);
    }

    @Override
    public void updateTennisPlayer(CreateTennisPlayerDTO tennisPlayerDTO, Long id) {
        TennisPlayer tennisPlayer = tennisPlayerRepository.getTennisPlayerByEmail(tennisPlayerDTO.getEmail());
        if(tennisPlayer != null) {
            throw new TennisException(HttpStatus.BAD_REQUEST,"Tennis Player with that email already exist!");
        }
        TennisPlayer updatedTennisPlayer = createTennisPlayerMapper.map(tennisPlayerDTO);
        updatedTennisPlayer.setId(id);
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
}
