package com.geek.back.services;

import com.geek.back.models.Carrito;
import com.geek.back.repositories.CarritoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;

    public CarritoServiceImpl(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Carrito> findAll() {
        return carritoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Carrito> findById(Long id) {
        return carritoRepository.findById(id);
    }

    @Override
    public Carrito save(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    @Override
    public Optional<Carrito> deleteById(Long id) {
        return carritoRepository.findById(id).map(c -> {
            carritoRepository.deleteById(id);
            return c;
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Carrito> findByUsuarioId(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
    }
}
