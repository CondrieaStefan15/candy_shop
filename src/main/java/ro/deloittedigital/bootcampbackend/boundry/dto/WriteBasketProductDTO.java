package ro.deloittedigital.bootcampbackend.boundry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WriteBasketProductDTO {

    private Long productId;

    private Long quantity;

}
