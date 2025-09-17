package com.geek.back.services;

import com.geek.back.models.ShoppingCart;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartService extends Service<ShoppingCart>{
    List<ShoppingCart> findByUsuarioId(Long usuarioId);
}
