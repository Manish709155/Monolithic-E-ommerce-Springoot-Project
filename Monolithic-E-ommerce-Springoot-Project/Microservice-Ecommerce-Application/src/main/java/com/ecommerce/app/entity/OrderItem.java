package com.ecommerce.app.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name="OrderItem")
@Table(name="order_item")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem extends SuperEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "price")
    BigDecimal price;


    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    Product product;
}
