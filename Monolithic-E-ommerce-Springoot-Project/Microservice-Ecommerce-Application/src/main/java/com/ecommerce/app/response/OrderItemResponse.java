package com.ecommerce.app.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class OrderItemResponse implements Serializable {

    Long orderItemId;

    Integer quantity;

    BigDecimal price;

    BigDecimal subTotalPrice;
}
