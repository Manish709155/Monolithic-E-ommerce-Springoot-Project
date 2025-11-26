package com.ecommerce.app.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    PENDING("Pending"),CONFIRMED("Confirmed"),
    SHIPPED("Shipped"),DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private final String getOrderStatus;
}
