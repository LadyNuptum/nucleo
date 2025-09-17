package com.geek.back.services;

import com.geek.back.models.Carrito;
import java.util.List;
import java.util.Optional;

public interface CarritoService {
    List<Carrito> findAll();
    Optional<Carrito> findById(Long id);
    Carrito save(Carrito carrito);
    Optional<Carrito> deleteById(Long id);
    List<Carrito> findByUsuarioId(Long usuarioId);
}
