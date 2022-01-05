package ro.deloittedigital.bootcampbackend.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.deloittedigital.bootcampbackend.entity.model.Basket;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> findBySessionId(String sessionId);

}
