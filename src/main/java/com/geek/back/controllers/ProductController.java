package com.geek.back.controllers;

import com.geek.back.models.Category;
import com.geek.back.models.Product;
import com.geek.back.models.ProductImage;
import com.geek.back.services.CategoryServiceImpl;
import com.geek.back.services.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;
    private final CategoryServiceImpl categoryService;

    public ProductController(ProductServiceImpl productService, CategoryServiceImpl categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // Listar todos los productos
    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(productService.findAll());
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> details(@PathVariable Long id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un producto
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        Set<Category> categories = product.getCategories().stream()
                .map(c -> categoryService.findByIdOrThrow(c.getId()))
                .collect(Collectors.toSet());
        product.setCategories(categories);

        Product savedProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // Actualizar un producto (sin sobrescribir imágenes)
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product) {
        return productService.findById(id)
                .map(productDb -> {
                    productDb.setName(product.getName());
                    productDb.setDescription(product.getDescription());
                    productDb.setPrice(product.getPrice());
                    productDb.setStock(product.getStock());

                    // Actualizar categorías
                    Set<Category> categories = product.getCategories().stream()
                            .map(c -> categoryService.findByIdOrThrow(c.getId()))
                            .collect(Collectors.toSet());
                    productDb.setCategories(categories);

                    Product updatedProduct = productService.save(productDb);
                    return ResponseEntity.ok(updatedProduct);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return productService.deleteById(id)
                .map(p -> ResponseEntity.noContent().<Void>build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Agregar una imagen a un producto
    @PostMapping("/{id}/images")
    public ResponseEntity<Product> addImage(@PathVariable Long id, @RequestBody ProductImage image) {
        Product updatedProduct = productService.addImageToProduct(id, image);
        return ResponseEntity.ok(updatedProduct);
    }

    // Eliminar una imagen de un producto
    @DeleteMapping("/{productId}/images/{imageId}")
    public ResponseEntity<Product> removeImage(@PathVariable Long productId, @PathVariable Long imageId) {
        Product updatedProduct = productService.removeImageFromProduct(productId, imageId);
        return ResponseEntity.ok(updatedProduct);
    }
}
