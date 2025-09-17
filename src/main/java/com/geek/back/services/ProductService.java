package com.geek.back.services;

import com.geek.back.models.Product;
import com.geek.back.models.ProductImage;

import java.util.List;
import java.util.Optional;

public interface ProductService extends Service<Product> {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Optional<Product> deleteById(Long id);

    // Nuevo método para categorías
    List<Product> findByCategoryName(String categoryName);

    Product addImageToProduct(Long productId, ProductImage image);
    Product removeImageFromProduct(Long productId, Long imageId);
}
