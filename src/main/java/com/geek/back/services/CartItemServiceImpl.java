package com.geek.back.services;

import com.geek.back.models.CartItem;
import com.geek.back.repositories.CartItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CartItem> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public CartItem save(CartItem detail) {
        return cartItemRepository.save(detail);
    }

    @Override
    public Optional<CartItem> deleteById(Long id) {
        return cartItemRepository.findById(id).map(d -> {
            cartItemRepository.deleteById(id);
            return d;
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItem> findByCarritoId(Long cartId) {
        return cartItemRepository.findByShoppingCart_Id(cartId);
    }
}
