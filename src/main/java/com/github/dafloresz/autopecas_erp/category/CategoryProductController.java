package com.github.dafloresz.autopecas_erp.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryProductController {

    private final CategoryProductService categoryService;

    public CategoryProductController(CategoryProductService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryProduct> create(@RequestBody CategoryProduct categoryProduct) {
        categoryProduct = categoryService.save(categoryProduct);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoryProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryProduct);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryProduct> update(@PathVariable Long id, @RequestBody CategoryProduct categoryProduct) {
        return ResponseEntity.ok().body(categoryService.update(id, categoryProduct));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<CategoryProduct> patch(@PathVariable Long id, @RequestBody CategoryProduct categoryProduct) {
        return ResponseEntity.ok().body(categoryService.patch(id, categoryProduct));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryProduct> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.findById(id));
    }


    @GetMapping("/search")
    public ResponseEntity<CategoryProduct> findByName(@RequestParam String name){
        return categoryService.findByName(name)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CategoryProduct>> findAll() {
        return ResponseEntity.ok().body(categoryService.findAll());
    }

}
