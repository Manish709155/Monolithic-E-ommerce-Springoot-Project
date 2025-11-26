package com.ecommerce.app.serviceImpl;

import com.ecommerce.app.entity.Product;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private  final ProductRepository productRepository;

    @Override
    public void createProduct(Product product) {

        productRepository.save(product);
    }

    @Override
    public Product findProductById(Long productId) {
        return productRepository.findByActiveTrueAndAndDeletedFalseAndId(productId);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public List<Product> findProductByActiveTrueAndKeyword(String searchKeyword) {
        return productRepository.findProductByActiveTrueAndDeletedFalseAndKeyword(searchKeyword);
    }
}
