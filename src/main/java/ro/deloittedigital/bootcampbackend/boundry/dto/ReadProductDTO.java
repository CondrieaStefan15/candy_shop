package ro.deloittedigital.bootcampbackend.boundry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.deloittedigital.bootcampbackend.entity.enums.ProductType;
import ro.deloittedigital.bootcampbackend.entity.model.Category;
import ro.deloittedigital.bootcampbackend.entity.model.Ingredient;
import ro.deloittedigital.bootcampbackend.entity.model.Rate;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Currency;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadProductDTO {

    private String title;
    private String shortDescription;
    private String longDescription;
    private Set<Ingredient> ingredients;
    private Integer quantity;
    private List<String> allergensList;
    private BigDecimal price;
    private Currency currency;
    private URL photoUrl;
    private ProductType productType;
    private Set<Rate> rates;
    private Category category;

}
