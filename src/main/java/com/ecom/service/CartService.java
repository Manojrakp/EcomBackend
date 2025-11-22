package com.ecom.service;


import com.ecom.entity.Cart;
import com.ecom.entity.CartItem;
import com.ecom.entity.EcomUser;
import com.ecom.entity.Product;
import com.ecom.repository.CartItemRepository;
import com.ecom.repository.CartRepository;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public Cart addToCart(Long userId, Long productId, int quantity) {
        // 1. Get the User
        EcomUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Get the Product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 3. Get or Create Cart
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        // 4. Check if Item exists in Cart
        // We use the repository helper to find the specific item line
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem(cart, product, quantity);
            cart.addCartItem(cartItem);
        }

        // 5. Recalculate Totals and Save
        recalculateTotal(cart);
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeFromCart(Long userId, Long cartItemId) {
        Cart cart = cartRepository.findByUserId(userId);
        CartItem itemToRemove = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Ensure this item belongs to this user's cart
        if (!itemToRemove.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("Item does not belong to user cart");
        }

       cart.removeCartItem(itemToRemove);
        cartItemRepository.delete(itemToRemove);

        recalculateTotal(cart);
        return cartRepository.save(cart);
    }

    public Cart getCart(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    private void recalculateTotal(Cart cart) {
        double total = 0;
        if (cart.getCartItems() != null) {
            for (CartItem item : cart.getCartItems()) {
                total += item.getProduct().getPrice() * item.getQuantity();
            }
        }
        cart.setTotalPrice(total);
    }
}
