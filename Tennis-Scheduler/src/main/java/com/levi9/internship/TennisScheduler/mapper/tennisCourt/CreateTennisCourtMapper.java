package com.levi9.internship.TennisScheduler.mapper.tennisCourt;

import com.levi9.internship.TennisScheduler.model.TennisCourt;
import com.levi9.internship.TennisScheduler.modelDTO.tennisCourt.CreateTennisCourtDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateTennisCourtMapper {
    CreateTennisCourtDTO map(TennisCourt tennisCourt);
    TennisCourt map(CreateTennisCourtDTO source);
}
