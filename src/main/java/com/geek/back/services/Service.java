package com.geek.back.services;
import java.util.List;
import java.util.Optional;

public interface Service<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    T save(T t);
    Optional<T> deleteById(Long id);
}
