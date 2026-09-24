package com.github.dafloresz.autopecas_erp.product;

import com.github.dafloresz.autopecas_erp.category.CategoryProduct;
import com.github.dafloresz.autopecas_erp.category.CategoryProductService;
import com.github.dafloresz.autopecas_erp.product.dto.ProductRequestDTO;
import com.github.dafloresz.autopecas_erp.product.dto.ProductResponseDTO;
import com.github.dafloresz.autopecas_erp.product.mapper.ProductMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryProductService categoryProductService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService,  CategoryProductService categoryProductService, ProductMapper productMapper) {
        this.productService = productService;
        this.categoryProductService = categoryProductService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO request) {
        var categoryProduct = categoryProductService.findById(request.categoryID());
        Product product = productMapper.toProduct(request,  categoryProduct);
        product = productService.save(product);
        var response = productMapper.productResponseDTO(product);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody ProductRequestDTO request) {
        var categoryProduct = categoryProductService.findById(request.categoryID());
        var product = productMapper.toProduct(request,  categoryProduct);
        product = productService.update(id, product);
        var response = productMapper.productResponseDTO(product);

       return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> patch(@PathVariable Long id, @RequestBody ProductRequestDTO request) {
        CategoryProduct categoryProduct = request.categoryID() != null
                ? categoryProductService.findById(request.categoryID())
                : productService.findById(id).getCategory();

        var product = productMapper.toProductChange(request,  categoryProduct);
        product = productService.patch(id, product);
        var response = productMapper.productResponseDTO(product);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        var response = productMapper.productResponseDTO(productService.findById(id));

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> findByName(@RequestParam String name) {
        List<ProductResponseDTO> products =  productService.findByName(name)
                .stream()
                .map(productMapper::productResponseDTO)
                .toList();

        return ResponseEntity.ok().body(products);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(@RequestParam(required = false, defaultValue = "") String category) {
        List<ProductResponseDTO> products;

        if(!category.isEmpty()) {
            products = productService.findByCategoryName(category)
                    .stream()
                    .map(productMapper::productResponseDTO)
                    .toList();

        } else {
            products = productService.findAll()
                    .stream()
                    .map(productMapper::productResponseDTO)
                    .toList();
        }

        return ResponseEntity.ok().body(products);
    }
}


