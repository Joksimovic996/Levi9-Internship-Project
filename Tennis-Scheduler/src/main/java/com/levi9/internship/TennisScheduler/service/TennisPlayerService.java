package com.levi9.internship.TennisScheduler.service;

import com.levi9.internship.TennisScheduler.model.TennisPlayer;
import com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer.CreateTennisPlayerDTO;
import com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer.TennisPlayerDTO;

import java.util.List;

public interface TennisPlayerService {


    TennisPlayerDTO getTennisPlayer(Long id);

    List<TennisPlayerDTO> getAllPlayers();

    TennisPlayerDTO addTennisPlayer(CreateTennisPlayerDTO tennisCourtDTO, String role);

    void updateTennisPlayer(CreateTennisPlayerDTO tennisPlayerDTO, Long id);

    void deleteTennisPlayer(Long id);

    TennisPlayerDTO getTennisPlayerByEmail(String email);

    TennisPlayerDTO giveMeBackMyAccount(String email);
}
