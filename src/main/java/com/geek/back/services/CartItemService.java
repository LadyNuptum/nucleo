package com.geek.back.services;

import com.geek.back.models.CartItem;
import java.util.List;

public interface CartItemService extends Service<CartItem>{
    List<CartItem> findByCarritoId(Long carritoId);
}
