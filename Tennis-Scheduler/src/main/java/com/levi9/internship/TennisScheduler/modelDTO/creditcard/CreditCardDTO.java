package com.levi9.internship.TennisScheduler.modelDTO.creditcard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Details about credit card")
public class CreditCardDTO {
    @ApiModelProperty(notes = "Credit card number with 16 characters")
    private Long creditCardNumber;
    @ApiModelProperty(notes = "Credit card valid date in format \"MM/YY\"")
    private String creditCardValidDate;
    @ApiModelProperty(notes = "Credit card CVC code with 3 characters")
    private int creditCardCvcCode;
}
