package com.ecommerce.app.response;

import com.ecommerce.app.constant.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductListResponse implements Serializable {

    Long id;

    String name;

    String description;

    BigDecimal price;

    Integer stockQuantity;

    Category category;

    String imageUrl;
}
