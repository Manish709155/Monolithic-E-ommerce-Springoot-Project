package com.ecommerce.app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name="ItemCart")
@Table(name = "item_cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemCart extends SuperEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    Product product;

    @Column(name = "price")
    BigDecimal price;

    @Column(name = "quantity")
    Integer quantity;


}
