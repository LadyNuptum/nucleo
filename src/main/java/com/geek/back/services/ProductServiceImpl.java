package com.geek.back.services;

import com.geek.back.models.Product;
import com.geek.back.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    final private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

//    @Transactional
//    @Override
//    public Optional<Product> deleteById(Long id) {
//        Optional<Product> optionalProduct = productRepository.findById(id);
//        if (optionalProduct.isPresent()){
//            productRepository.deleteById(id);
//            return optionalProduct;
//        }
//        return Optional.empty();
//    }

    @Transactional
    @Override
    public Optional <Product> deleteById(Long id){
        return productRepository.findById(id).map(p ->{
            productRepository.deleteById(id);
            return p;
        });
    }

}

