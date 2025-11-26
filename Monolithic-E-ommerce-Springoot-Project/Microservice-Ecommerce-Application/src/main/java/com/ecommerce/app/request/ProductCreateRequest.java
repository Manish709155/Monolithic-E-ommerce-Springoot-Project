package com.ecommerce.app.request;

import com.ecommerce.app.constant.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
public class ProductCreateRequest implements Serializable {

    String name;

    String description;

    BigDecimal price;

    Integer stockQuantity;

    Category category;

    String imageUrl;
}
