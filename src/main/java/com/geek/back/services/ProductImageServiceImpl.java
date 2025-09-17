package com.geek.back.services;

import com.geek.back.models.ProductImage;
import com.geek.back.repositories.ProductImageRepository;
import com.geek.back.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageServiceImpl implements ProductImageService{

    final private ProductImageRepository imageRepository;

    public ProductImageServiceImpl(ProductImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductImage> findAll() {
        return imageRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ProductImage> findById(Long id) {
        return imageRepository.findById(id);
    }

    @Transactional
    @Override
    public ProductImage save(ProductImage productImage) {
        return imageRepository.save(productImage);
    }

    @Transactional
    @Override
    public Optional<ProductImage> deleteById(Long id) {
        return imageRepository.findById(id).map(i ->{
            imageRepository.deleteById(id);
            return i;
        });
    }

}
