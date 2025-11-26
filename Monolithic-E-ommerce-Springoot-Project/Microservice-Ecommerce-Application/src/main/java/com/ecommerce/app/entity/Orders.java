package com.ecommerce.app.entity;

import com.ecommerce.app.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Orders")
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orders extends SuperEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "total_amount")
    BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    OrderStatus  orderStatus;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    User user;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL,orphanRemoval = true)
    List<OrderItem> orderItems = new ArrayList<>();
}
