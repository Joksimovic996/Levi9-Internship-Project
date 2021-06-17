package com.levi9.internship.TennisScheduler.mapper.tennisCourt;

import com.levi9.internship.TennisScheduler.model.TennisCourt;
import com.levi9.internship.TennisScheduler.modelDTO.tennisCourt.UpdateTennisCourtDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdateTennisCourtMapper {
    UpdateTennisCourtDTO map(TennisCourt tennisCourt);
    TennisCourt map(UpdateTennisCourtDTO source);
}
