package com.geek.back.controllers;

import com.geek.back.models.Rol;
import com.geek.back.services.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<List<Rol>> list() {
        return ResponseEntity.of(Optional.ofNullable(rolService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> details(@PathVariable Long id) {
        Optional<Rol> op = rolService.findById(id);
        return op.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Rol> create(@RequestBody Rol rol) {
        Rol saved = rolService.save(rol);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Rol> update(@RequestBody Rol rol, @PathVariable Long id) {
        rol.setId(id);
        Rol saved = rolService.save(rol);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}