package com.levi9.internship.TennisScheduler.mapper.tennisPlayer;


import com.levi9.internship.TennisScheduler.model.TennisPlayer;
import com.levi9.internship.TennisScheduler.modelDTO.tennisPlayer.CreateTennisPlayerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateTennisPlayerMapper {
    @Mapping(target = "password",ignore = true)
    CreateTennisPlayerDTO map(TennisPlayer tennisCourt);
    @Mapping(target = "password",ignore = true)
    TennisPlayer map(CreateTennisPlayerDTO source);
}
