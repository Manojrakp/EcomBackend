package com.ecom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq_gen")
    @SequenceGenerator(name = "cart_seq_gen", sequenceName = "cart_seq", allocationSize = 1)
    private Long id;

    // Link to the user (One user usually has one active cart)
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "id")
    private EcomUser user;

    // The list of items in the cart
    // CascadeType.ALL means if we delete the Cart, we delete the Items
    // orphanRemoval = true means if we remove an item from this list, it is deleted from DB
    @JsonBackReference
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();


    private Double totalPrice = 0.0;

    // Helper method to add items easily
    public void addCartItem(CartItem item) {
        this.cartItems.add(item);
        item.setCart(this); // bi-directional link
    }

    public void removeCartItem(CartItem itemToRemove) {
        this.cartItems.remove(itemToRemove);
        itemToRemove.setCart(this);
    }
}
