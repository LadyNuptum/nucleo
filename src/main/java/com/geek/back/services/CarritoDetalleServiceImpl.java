package com.geek.back.services;

import com.geek.back.models.CarritoDetalle;
import com.geek.back.repositories.CarritoDetalleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarritoDetalleServiceImpl implements CarritoDetalleService {

    private final CarritoDetalleRepository carritoDetalleRepository;

    public CarritoDetalleServiceImpl(CarritoDetalleRepository carritoDetalleRepository) {
        this.carritoDetalleRepository = carritoDetalleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarritoDetalle> findAll() {
        return carritoDetalleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CarritoDetalle> findById(Long id) {
        return carritoDetalleRepository.findById(id);
    }

    @Override
    public CarritoDetalle save(CarritoDetalle detalle) {
        return carritoDetalleRepository.save(detalle);
    }

    @Override
    public Optional<CarritoDetalle> deleteById(Long id) {
        return carritoDetalleRepository.findById(id).map(d -> {
            carritoDetalleRepository.deleteById(id);
            return d;
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarritoDetalle> findByCarritoId(Long carritoId) {
        return carritoDetalleRepository.findByCarritoId(carritoId);
    }
}
