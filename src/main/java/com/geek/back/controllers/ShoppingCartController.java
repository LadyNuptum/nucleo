package com.geek.back.controllers;

import com.geek.back.models.ShoppingCart;
import com.geek.back.services.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCart>> list() {
        return ResponseEntity.ok(shoppingCartService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> details(@PathVariable Long id) {
        return shoppingCartService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{usuarioId}")
    public ResponseEntity<List<ShoppingCart>> listByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(shoppingCartService.findByUsuarioId(usuarioId));
    }

    @PostMapping("/create")
    public ResponseEntity<ShoppingCart> create(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart saved = shoppingCartService.save(shoppingCart);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ShoppingCart> update(@PathVariable Long id, @RequestBody ShoppingCart shoppingCart) {
        shoppingCart.setId(id);
        ShoppingCart saved = shoppingCartService.save(shoppingCart);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        shoppingCartService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
