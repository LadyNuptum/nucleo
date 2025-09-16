package com.geek.back.controllers;


import com.geek.back.models.Category;
import com.geek.back.models.Product;
import com.geek.back.services.CategoryServiceImpl;
import com.geek.back.services.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController

public class ProductController {

    final private ProductServiceImpl service;
    final private CategoryServiceImpl categoryService;

    public ProductController(ProductServiceImpl service, CategoryServiceImpl categoryService) {
        this.service = service;
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity<List<Product>> list(){
        return ResponseEntity.of(Optional.ofNullable(service.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> details(@PathVariable Long id){
        Optional<Product> optionalProduct = service.findById(id);
        if (optionalProduct.isPresent()){
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Product> update(@Valid @RequestBody Product product, @PathVariable Long id){
        Optional<Product> optionalProduct = service.findById(id);
        if (optionalProduct.isPresent()){
            Product productDb = optionalProduct.orElseThrow();
            productDb.setName(product.getName());
            productDb.setDescription(product.getDescription());
            productDb.setPrice(product.getPrice());
            productDb.setStock(product.getStock());
            productDb.setUpdatedAt(LocalDateTime.now());

            Set<Category> categories = product.getCategories().stream()
                    .map(c -> categoryService.findByIdOrThrow(c.getId()))
                    .collect(Collectors.toSet());

            productDb.setCategories(categories);
            productDb.setImages(product.getImages());

            return ResponseEntity.ok(service.save(productDb));
        }
        return ResponseEntity.notFound().build();
    }
}
