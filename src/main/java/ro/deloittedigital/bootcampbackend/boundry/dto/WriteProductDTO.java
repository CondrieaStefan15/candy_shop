package ro.deloittedigital.bootcampbackend.boundry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.deloittedigital.bootcampbackend.boundry.customvalidator.constraint.ValueOfEnum;
import ro.deloittedigital.bootcampbackend.entity.enums.ProductType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WriteProductDTO {


    @NotEmpty(message = "Please provide a title!")
    private String title;

    @NotEmpty(message = "Please provide a short description")
    @Size(min = 1, max = 200, message = "Short description should be less than 50 characters")
    private String shortDescription;

    @NotEmpty(message = "Please provide a long description")
    @Size(min = 1, max = 400, message = "Long description should be less than 200 characters")
    private String longDescription;


    @NotNull(message = "Please provide a quantity")
    private Integer quantity;

    @NotNull(message = "Please provide a price")
    private BigDecimal price;

    @NotNull
    private Currency currency;

    @NotNull
    private URL photoUrl;


    @Enumerated(EnumType.STRING)
    @NotNull(message = "Please provide a product type")
    private ProductType productType;

}
