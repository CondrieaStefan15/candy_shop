package ro.deloittedigital.bootcampbackend.boundry.dto;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadBasketProductDTO {


    private String title;

    private Long quantity;


    private BigDecimal price;


    private BigDecimal total_price;
}
