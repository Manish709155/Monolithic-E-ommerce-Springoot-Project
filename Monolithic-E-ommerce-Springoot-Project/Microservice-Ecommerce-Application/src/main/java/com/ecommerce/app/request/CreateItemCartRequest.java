package com.ecommerce.app.request;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateItemCartRequest {

   Long userId;

   Long productId;

   Integer quantity;
}
