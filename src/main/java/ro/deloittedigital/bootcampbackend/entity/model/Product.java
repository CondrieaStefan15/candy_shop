package ro.deloittedigital.bootcampbackend.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.deloittedigital.bootcampbackend.boundry.customvalidator.constraint.ValueOfEnum;
import ro.deloittedigital.bootcampbackend.entity.enums.ProductType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;


@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "long_description")
    private String longDescription;

    @ManyToMany(cascade = ALL, fetch = LAZY)
    @JoinColumn(name = "ingredient_id")
    private Set<Ingredient> ingredientsList;

    @Column(name = "quantity")
    @NotNull
    private Integer quantity;

    @NotNull
    @ElementCollection
    private List<String> allergensList;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @Column(name = "currency")
    @NotNull
    private Currency currency;

    @Column(name = "photo_url", length = 500)
    @NotNull
    private URL photoUrl;

    @Column(name = "product_type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @OneToMany(mappedBy = "product", cascade = ALL, fetch = LAZY)
    @JsonIgnore
    private Set<Rate> rates = new HashSet<>();

    @ManyToOne(cascade = {PERSIST, MERGE, REFRESH, DETACH}, fetch = EAGER)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_category_id"))
    private Category category;


}
