package com.geek.back.services;

import com.geek.back.models.ShoppingCart;
import com.geek.back.repositories.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ShoppingCart> findById(Long id) {
        return shoppingCartRepository.findById(id);
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> deleteById(Long id) {
        return shoppingCartRepository.findById(id).map(c -> {
            shoppingCartRepository.deleteById(id);
            return c;
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShoppingCart> findByUsuarioId(Long usuarioId) {
        return shoppingCartRepository.findByUserId(usuarioId);
    }
}
