package com.ecom.controller;

import com.ecom.dto.ProductDetailsRecord;
import com.ecom.entity.Cart;
import com.ecom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/item/add")
    public ResponseEntity<Cart> addToCart(@RequestBody Map<String, Object> body) {
        Long userId = ((Number) body.get("userId")).longValue();
        Long productId = ((Number) body.get("productId")).longValue();
        int quantity = ((Number) body.get("quantity")).intValue();

        return ResponseEntity.ok(cartService.addToCart(userId, productId, quantity));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @DeleteMapping("/item/delete")
    public ResponseEntity<Cart> removeFromCart(@RequestBody Map<String, Long> body) {
        Long userId = body.get("userId");
        Long cartItemId = body.get("cartItemId");

        return ResponseEntity.ok(cartService.removeFromCart(userId, cartItemId));
    }

    @PutMapping("/item/modify")
    public ResponseEntity<Cart> updateQuantity(@RequestBody Map<String, Object> body) {
        Long userId = ((Number) body.get("userId")).longValue();
        Long cartItemId = ((Number) body.get("cartItemId")).longValue();
        int quantity = ((Number) body.get("quantity")).intValue();

        return ResponseEntity.ok(cartService.updateQuantity(userId, cartItemId, quantity));
    }
}
