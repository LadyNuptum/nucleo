package com.geek.back.controllers;

import com.geek.back.models.ProductImage;
import com.geek.back.services.ProductImageServiceImpl;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ProductImageController {

    final private ProductImageServiceImpl imageService;

    public ProductImageController(ProductImageServiceImpl imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<List<ProductImage>> list(){
        return ResponseEntity.of(Optional.ofNullable(imageService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductImage> details(@PathVariable Long id){
        Optional<ProductImage> image = imageService.findById(id);
        if (image.isPresent()){
            return ResponseEntity.ok(image.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<ProductImage> create(@Valid @RequestBody ProductImage image){
       return ResponseEntity.status(HttpStatus.CREATED).body(imageService.save(image));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ProductImage> update(@Valid @RequestBody ProductImage image, @PathVariable Long id){
        Optional<ProductImage> imageOptional = imageService.findById(id);
        if (imageOptional.isPresent()){
            ProductImage imageDb = imageOptional.orElseThrow();
            imageDb.setUrl(image.getUrl());
            imageDb.setMainImage(image.isMainImage());
            imageDb.setProduct(image.getProduct());

            return ResponseEntity.ok(imageService.save(imageDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductImage> delete(@PathVariable Long id){
        Optional<ProductImage> imageOptional = imageService.deleteById(id);
        if (imageOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(imageOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
