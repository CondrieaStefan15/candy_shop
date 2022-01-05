package ro.deloittedigital.bootcampbackend.entity.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "basket_products")
public class BasketProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_product_id"))
    @NotNull
    private Product product;

    @NotNull
    private Long quantity;

    @NotNull
    private BigDecimal price;

    @NotNull
    private BigDecimal total_price;

}
