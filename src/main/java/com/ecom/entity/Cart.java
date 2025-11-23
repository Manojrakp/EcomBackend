package com.ecom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference // added so that user datas are not part of this entity
    @JoinColumn(name = "user_id")
    private EcomUser user;

    // CascadeType.ALL means if we delete the Cart, we delete the Items
    // orphanRemoval = true means if we remove an item from this list, it is deleted from DB
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<CartItem> cartItems = new HashSet<>();

    private Double totalPrice = 0.0;

    // Helper method to add items easily
    public void addCartItem(CartItem item) {
        this.cartItems.add(item);
        item.setCart(this); // bi-directional link
    }

    public void removeCartItem(CartItem itemToRemove) {
        this.cartItems.remove(itemToRemove);
        itemToRemove.setCart(null);
    }
}
