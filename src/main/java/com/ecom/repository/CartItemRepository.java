package com.ecom.repository;

import com.ecom.entity.CartItem;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Useful to check if a product is already in the cart to just update quantity
    CartItem findByCartIdAndProductId(Long cartId, Long productId);
}
