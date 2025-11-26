package com.ecommerce.app.service;

import com.ecommerce.app.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void createProduct(Product product);

    Product findProductById(Long productId);

    List<Product> getProducts();

    List<Product> findProductByActiveTrueAndKeyword(String searchKeyword);


}
