package ro.deloittedigital.bootcampbackend.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.deloittedigital.bootcampbackend.entity.model.Ingredient;


public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
