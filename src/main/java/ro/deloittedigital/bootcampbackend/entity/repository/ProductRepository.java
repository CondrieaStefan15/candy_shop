package ro.deloittedigital.bootcampbackend.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.deloittedigital.bootcampbackend.entity.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
