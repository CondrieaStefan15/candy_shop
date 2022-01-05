package ro.deloittedigital.bootcampbackend.services.interfaces;

import org.springframework.stereotype.Service;
import ro.deloittedigital.bootcampbackend.boundry.dto.CategoryDTO;
import ro.deloittedigital.bootcampbackend.entity.model.Category;

import java.util.List;

@Service
public interface CategoryService {

    CategoryDTO getCategoryById(Long id);

    Category createCategory(CategoryDTO category);

    Category updateCategory(Long categoryId, CategoryDTO category);

    List<CategoryDTO> getAll();

}