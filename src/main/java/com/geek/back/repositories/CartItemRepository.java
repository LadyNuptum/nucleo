package com.geek.back.repositories;

import com.geek.back.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByShoppingCart_Id(Long carritoId);
}
