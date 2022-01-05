package ro.deloittedigital.bootcampbackend.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "rates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = ALL, fetch = LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @Column
    @PositiveOrZero(message = "Rate should be a positive number")
    @Max(value = 5, message = "Rate should not exceed 5")
    private int rate;

}
