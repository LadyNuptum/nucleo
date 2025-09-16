package com.geek.back.services;

import com.geek.back.models.Category;
import com.geek.back.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> deleteById(Long id) {
        return categoryRepository.findById(id).map(c ->{
            categoryRepository.deleteById(id);
            return c;
        });

    }

    public Category findByIdOrThrow(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found " + id));
    }
}
