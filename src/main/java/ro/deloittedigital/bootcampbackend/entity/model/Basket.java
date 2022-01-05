package ro.deloittedigital.bootcampbackend.entity.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@Table(name = "baskets")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String sessionId;

    @OneToMany(cascade = ALL, fetch = LAZY)
    @JoinColumn(name = "basket_id")
    private Set<BasketProduct> products = new HashSet<>();


}
