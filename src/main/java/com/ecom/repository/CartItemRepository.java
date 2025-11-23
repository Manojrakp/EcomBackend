package com.ecom.repository;

import com.ecom.entity.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Useful to check if a product is already in the cart to just update quantity
    CartItem findByCartIdAndProductId(Long cartItemId, Long productId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.cart.id = :cartId")
    void deleteAllByCartId(Long cartId);

    @Query("SELECT c FROM CartItem c WHERE c.cart.id = :cartId")
    List<CartItem> findAllItemsByCartId(@Param("cartId") Long cartId);
}
