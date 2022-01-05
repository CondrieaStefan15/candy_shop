package ro.deloittedigital.bootcampbackend.boundry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RateDTO {

    private Long productId;

    @PositiveOrZero(message = "Rate should be a positive number")
    @Max(value = 5, message = "Rate should not exceed 5")
    private int rate;

}
