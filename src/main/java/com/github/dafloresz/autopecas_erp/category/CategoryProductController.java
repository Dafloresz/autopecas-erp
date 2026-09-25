package com.github.dafloresz.autopecas_erp.category;

import com.github.dafloresz.autopecas_erp.category.dto.CategoryProductRequestDTO;
import com.github.dafloresz.autopecas_erp.category.dto.CategoryProductResponseDTO;
import com.github.dafloresz.autopecas_erp.category.mapper.CategoryProductMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryProductController {

    private final CategoryProductService categoryService;
    private final CategoryProductMapper  categoryMapper;

    public CategoryProductController(CategoryProductService categoryService,  CategoryProductMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public ResponseEntity<CategoryProductResponseDTO> create(@RequestBody CategoryProductRequestDTO categoryRequestDTO) {
        var categoryProduct = categoryMapper.toCategoryProduct(categoryRequestDTO);
        categoryProduct = categoryService.save(categoryProduct);
        var response = categoryMapper.toCategoryResponseDTO(categoryProduct);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoryProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryProductResponseDTO> update(@PathVariable Long id, @RequestBody CategoryProductRequestDTO categoryRequestDTO) {
        var categoryProduct = categoryMapper.toCategoryProduct(categoryRequestDTO);
        categoryProduct = categoryService.update(id, categoryProduct);
        var response = categoryMapper.toCategoryResponseDTO(categoryProduct);

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<CategoryProductResponseDTO> patch(@PathVariable Long id, @RequestBody CategoryProductRequestDTO categoryRequestDTO) {
        var  categoryProduct = categoryMapper.toCategoryChange(categoryRequestDTO);
        categoryProduct = categoryService.patch(id, categoryProduct);
        var response = categoryMapper.toCategoryResponseDTO(categoryProduct);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryProductResponseDTO> findById(@PathVariable Long id) {
        var  categoryProduct = categoryService.findById(id);
        var response = categoryMapper.toCategoryResponseDTO(categoryProduct);

        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/search")
    public ResponseEntity<CategoryProductResponseDTO> findByName(@RequestParam String name){
        return categoryService.findByName(name)
                .map(categoryMapper::toCategoryResponseDTO)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CategoryProductResponseDTO>> findAll() {
        var response = categoryService.findAll()
                .stream()
                .map(categoryMapper::toCategoryResponseDTO)
                .toList();

        return ResponseEntity.ok().body(response);
    }

}
