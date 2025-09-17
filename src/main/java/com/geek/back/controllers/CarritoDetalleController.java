package com.geek.back.controllers;

import com.geek.back.models.CarritoDetalle;
import com.geek.back.services.CarritoDetalleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carrito-detalles")
public class CarritoDetalleController {

    private final CarritoDetalleService carritoDetalleService;

    public CarritoDetalleController(CarritoDetalleService carritoDetalleService) {
        this.carritoDetalleService = carritoDetalleService;
    }

    @GetMapping
    public ResponseEntity<List<CarritoDetalle>> list() {
        return ResponseEntity.ok(carritoDetalleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoDetalle> details(@PathVariable Long id) {
        return carritoDetalleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/carrito/{carritoId}")
    public ResponseEntity<List<CarritoDetalle>> listByCarrito(@PathVariable Long carritoId) {
        return ResponseEntity.ok(carritoDetalleService.findByCarritoId(carritoId));
    }

    @PostMapping("/create")
    public ResponseEntity<CarritoDetalle> create(@RequestBody CarritoDetalle detalle) {
        CarritoDetalle saved = carritoDetalleService.save(detalle);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CarritoDetalle> update(@PathVariable Long id, @RequestBody CarritoDetalle detalle) {
        detalle.setId(id);
        CarritoDetalle saved = carritoDetalleService.save(detalle);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carritoDetalleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
