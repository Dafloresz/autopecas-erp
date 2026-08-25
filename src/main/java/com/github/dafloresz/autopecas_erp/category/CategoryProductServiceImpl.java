package com.github.dafloresz.autopecas_erp.category;

import com.github.dafloresz.autopecas_erp.exception.ResourceNotFoundException;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryProductServiceImpl implements CategoryProductService {

    private final CategoryProductRepository categoryRepository;

    public CategoryProductServiceImpl(CategoryProductRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryProduct save(CategoryProduct categoryProduct) {
       if(existsByName(categoryProduct.getName())) {
           throw new EntityExistsException("Category with name " + categoryProduct.getName() + " already exists");
       }

       return categoryRepository.save(categoryProduct);
    }

    @Override
    public CategoryProduct update(Long id, CategoryProduct categoryProduct) {
        if(!(existsById(id))) {
            throw new ResourceNotFoundException("CategoryProduct with id " + id + " does not exist");
        }

        categoryProduct.setId(id);
        return categoryRepository.save(categoryProduct);
    }

    @Override
    public CategoryProduct patch(Long id, CategoryProduct changes) {
        CategoryProduct category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("CategoryProduct with id " + id + " does not exist"));

        if(changes.getName() != null) {
            category.setName(changes.getName());
        }

        if(changes.getDescription() != null) {
            category.setDescription(changes.getDescription());
        }

        return categoryRepository.save(category);
    }

    @Override
    public CategoryProduct findById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("id cannot be null");
        }

        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category Product with id " + id + " does not exist"));

    }

    @Override
    public void deleteById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("id cannot be null");
        }

        if(existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Category Product with id " + id + " does not exist");
        }

    }

    @Override
    public Optional<CategoryProduct> findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }

       return categoryRepository.findByName(name);
    }

    @Override
    public List<CategoryProduct> findAll() {
        return categoryRepository.findAll();
    }


    private boolean existsById(Long id) {
         return categoryRepository.existsById(id);
    }

    private boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
