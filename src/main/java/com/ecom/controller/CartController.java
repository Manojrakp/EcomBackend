package com.ecom.controller;



import com.ecom.entity.Cart;
import com.ecom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam Long userId,
                                          @RequestParam Long productId,
                                          @RequestParam int quantity) {
        Cart updatedCart = cartService.addToCart(userId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    // GET /api/cart/1
    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        Cart cart = cartService.getCart(userId);
        return ResponseEntity.ok(cart);
    }

    // DELETE /api/cart/1/item/50
    @DeleteMapping("/{userId}/item/{cartItemId}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Long userId,
                                               @PathVariable Long cartItemId) {
        Cart updatedCart = cartService.removeFromCart(userId, cartItemId);
        return ResponseEntity.ok(updatedCart);
    }
}
