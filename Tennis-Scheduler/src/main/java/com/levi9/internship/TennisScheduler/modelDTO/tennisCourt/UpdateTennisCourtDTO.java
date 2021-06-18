package com.levi9.internship.TennisScheduler.modelDTO.tennisCourt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(description = "Update tennis court DTO")
public class UpdateTennisCourtDTO {
    @ApiModelProperty(notes = "You can change type of court. Types of court: CLAY_WITH_ROOF, CLAY_WITHOUT_ROOF," + "GRASS_WITH_ROOF, GRASS_WITHOUT_ROOF, HARD_WITH_ROOF, HARD_WITHOUT_ROOF, CARPET_WITH_ROOF, CARPET_WITHOUT_ROOF")
    private String courtType;
    @ApiModelProperty(notes = "You can change price per minute")
    private Double pricePerMinute;
}
