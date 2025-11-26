package com.ecommerce.app.response;

import com.ecommerce.app.constant.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class OrderResponse implements Serializable {

    Long orderId;

    BigDecimal totalAmount;

    OrderStatus orderStatus;

    List<OrderItemResponse> orderItemResponses ;

    LocalDateTime orderCreatedOn;

}
