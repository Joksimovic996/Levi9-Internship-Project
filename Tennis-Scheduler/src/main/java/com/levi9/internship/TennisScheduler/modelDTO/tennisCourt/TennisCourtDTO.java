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
@ApiModel(description = "Details about the tennis court")
public class TennisCourtDTO {
    @ApiModelProperty(notes = "The unique ID of the tennis court")
    private Long id;
    @ApiModelProperty(notes = "The unique name of the tennis court")
    private String name;
    @ApiModelProperty(notes = "The court's type. Possible Values: CLAY_WITH_ROOF, CLAY_WITHOUT_ROOF, GRASS_WITH_ROOF, GRASS_WITHOUT_ROOF, HARD_WITH_ROOF, HARD_WITHOUT_ROOF, CARPET_WITH_ROOF, CARPET_WITHOUT_ROOF")
    private String courtType;
    @ApiModelProperty(notes = "The court's price per minute")
    private Double pricePerMinute;


}
