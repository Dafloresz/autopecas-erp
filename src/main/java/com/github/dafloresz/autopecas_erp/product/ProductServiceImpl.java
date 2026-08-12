package com.github.dafloresz.autopecas_erp.product;

import com.github.dafloresz.autopecas_erp.exception.ResourceNotFoundException;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        if (existsByName(product.getName())) {
            throw new EntityExistsException("Product with name " + product.getName() + " already exists");
        }

        return repository.save(product);
    }


    @Override
    public Product update(Product productToUpdate) {
        if (!(existsById(productToUpdate.getId()))) {
            throw new ResourceNotFoundException("Id doesn't exist");
        }

        return repository.save(productToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        if (existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Product with id " + id + " does not exist");

        }
    }

    @Override
    public Product findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
    }

    @Override
    public Optional<Product> findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        return repository.findByName(name);
    }

    @Override
    public List<Product> findByCategoryName(String category) {
        if (category == null) {
            throw new IllegalArgumentException("category is null");
        }
        return repository.findByCategory_NameContaining(category);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    private boolean existsByName(String name) {
        return repository.existsByName(name);
    }


    private boolean existsById(Long id) {
        return repository.existsById(id);
    }



}
