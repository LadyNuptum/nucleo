package com.geek.back.repositories;

import com.geek.back.models.CarritoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarritoDetalleRepository extends JpaRepository<CarritoDetalle, Long> {
    List<CarritoDetalle> findByCarritoId(Long carritoId);
}
