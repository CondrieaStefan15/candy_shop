package ro.deloittedigital.bootcampbackend.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.deloittedigital.bootcampbackend.entity.model.BasketProduct;

public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
}
