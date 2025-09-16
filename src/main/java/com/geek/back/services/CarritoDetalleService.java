package com.geek.back.services;

import com.geek.back.models.CarritoDetalle;
import java.util.List;
import java.util.Optional;

public interface CarritoDetalleService {
    List<CarritoDetalle> findAll();
    Optional<CarritoDetalle> findById(Long id);
    CarritoDetalle save(CarritoDetalle detalle);
    Optional<CarritoDetalle> deleteById(Long id);
    List<CarritoDetalle> findByCarritoId(Long carritoId);
}
