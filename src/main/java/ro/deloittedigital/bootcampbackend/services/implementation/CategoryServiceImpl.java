package ro.deloittedigital.bootcampbackend.services.implementation;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ro.deloittedigital.bootcampbackend.boundry.dto.CategoryDTO;
import ro.deloittedigital.bootcampbackend.boundry.exceptions.AlreadyExistsException;
import ro.deloittedigital.bootcampbackend.boundry.exceptions.BadRequestException;
import ro.deloittedigital.bootcampbackend.boundry.exceptions.NotFoundException;
import ro.deloittedigital.bootcampbackend.entity.model.Category;
import ro.deloittedigital.bootcampbackend.entity.repository.CategoryRepository;
import ro.deloittedigital.bootcampbackend.services.interfaces.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    @Override
    public Category createCategory(CategoryDTO category) {

        String categoryName = category.getName();
        int count = categoryRepository.findByName(categoryName).size();
        if (count > 0) {
            throw new AlreadyExistsException("Category[name=" + categoryName + "] already exists!");
        }

        if (category.getName() != null) {
            return categoryRepository.save(modelMapper.map(category, Category.class));
        }

        throw new BadRequestException("Category hasn't all the mandatory fields!");

    }

    @Override
    public Category updateCategory(Long categoryId, CategoryDTO category) {

        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        Category existingCategory = optionalCategory.get();
        if (category.getName() != null) {
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            return categoryRepository.save(existingCategory);
        }
        throw new BadRequestException("Category hasn't all the mandatory fields!");

    }

    @Override
    public CategoryDTO getCategoryById(Long id) {

        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        return modelMapper.map(category.get(), CategoryDTO.class);
    }

    public List<CategoryDTO> getAll() {

        return categoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());

    }

}


