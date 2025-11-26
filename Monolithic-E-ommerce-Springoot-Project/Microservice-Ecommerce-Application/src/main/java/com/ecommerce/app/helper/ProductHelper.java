package com.ecommerce.app.helper;

import com.ecommerce.app.entity.Product;
import com.ecommerce.app.request.ProductCreateRequest;
import com.ecommerce.app.response.*;
import com.ecommerce.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Component
public class ProductHelper {

    @Autowired
    ProductService productService;

    public Long createProduct(ProductCreateRequest productCreateRequest){
        Product product = new Product();
        product.setName(productCreateRequest.getName());
        product.setCategory(productCreateRequest.getCategory());
        product.setPrice(productCreateRequest.getPrice());
        product.setDescription(productCreateRequest.getDescription());
        product.setImageUrl(productCreateRequest.getImageUrl());
        product.setCreatedOn(LocalDateTime.now());
        product.setUpdatedOn(LocalDateTime.now());
        product.setStockQuantity(productCreateRequest.getStockQuantity());
        productService.createProduct(product);
        return  product.getId();

    }
    public List<ProductListResponse> getProducts() {
        List<Product> products=productService.getProducts();
        List<ProductListResponse> responses = new ArrayList<ProductListResponse>();
        products.forEach(product -> {

            responses.add(ProductListResponse.builder()
                    .id(product.getId())
                            .name(product.getName())
                            .price(product.getPrice())
                            .category(product.getCategory())
                            .description(product.getDescription())
                            .imageUrl(product.getImageUrl())
                            .stockQuantity(product.getStockQuantity())
                    .build());
        });
        return responses;
    }

    public ProductResponse getProduct(Long productId){
      Product product= productService.findProductById(productId);
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .stockQuantity(product.getStockQuantity())
                .build()
                ;
    }

    public List<ProductListResponse> searchProductByKeyword(String searchKeyword) {
        List<Product> products=productService.findProductByActiveTrueAndKeyword(searchKeyword);
        List<ProductListResponse> responses = new ArrayList<ProductListResponse>();
        products.forEach(product -> {
            responses.add(ProductListResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .category(product.getCategory())
                    .description(product.getDescription())
                    .imageUrl(product.getImageUrl())
                    .stockQuantity(product.getStockQuantity())
                    .build());
        });
        return responses;
    }

    public String updateProduct(ProductCreateRequest productCreateRequest,Long productId){
        Product product = productService.findProductById(productId);
        product.setName(productCreateRequest.getName());
        product.setCategory(productCreateRequest.getCategory());
        product.setPrice(productCreateRequest.getPrice());
        product.setDescription(productCreateRequest.getDescription());
        product.setImageUrl(productCreateRequest.getImageUrl());
        product.setCreatedOn(LocalDateTime.now());
        product.setUpdatedOn(LocalDateTime.now());
        product.setStockQuantity(productCreateRequest.getStockQuantity());
        productService.createProduct(product);
        return "Product has been updated successfully: " + product.getId();

    }
}
