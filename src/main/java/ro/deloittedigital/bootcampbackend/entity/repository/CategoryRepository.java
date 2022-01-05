package ro.deloittedigital.bootcampbackend.entity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ro.deloittedigital.bootcampbackend.entity.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByName(String categoryName);

}
