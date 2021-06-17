package com.levi9.internship.TennisScheduler.mapper.tennisPlayer;


import com.levi9.internship.TennisScheduler.model.TennisPlayer;
import com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer.TennisPlayerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TennisPlayerMapper {
    TennisPlayer map(TennisPlayerDTO source);
    TennisPlayerDTO map(TennisPlayer tennisPlayer);
}
