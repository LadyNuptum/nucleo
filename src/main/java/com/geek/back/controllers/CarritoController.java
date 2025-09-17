package com.geek.back.controllers;

import com.geek.back.models.Carrito;
import com.geek.back.services.CarritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carritos")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping
    public ResponseEntity<List<Carrito>> list() {
        return ResponseEntity.ok(carritoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> details(@PathVariable Long id) {
        return carritoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carrito>> listByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(carritoService.findByUsuarioId(usuarioId));
    }

    @PostMapping("/create")
    public ResponseEntity<Carrito> create(@RequestBody Carrito carrito) {
        Carrito saved = carritoService.save(carrito);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Carrito> update(@PathVariable Long id, @RequestBody Carrito carrito) {
        carrito.setId(id);
        Carrito saved = carritoService.save(carrito);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carritoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
