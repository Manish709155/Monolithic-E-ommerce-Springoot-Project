package com.ecommerce.app.entity;

import com.ecommerce.app.constant.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Product")
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends SuperEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;


    @Column(name="description")
    String description;

    @Column(name="price")
    BigDecimal price;

    @Column(name="stock_quantity")
    Integer stockQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    Category category;

    @Column(name="image_url")
    String imageUrl;

}
