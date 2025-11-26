package com.ecommerce.app.repository;

import com.ecommerce.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p where p.active=true AND p.deleted=false AND p.stockQuantity > 0 AND  p.id = :productId")
    Product findByActiveTrueAndAndDeletedFalseAndId(@Param("productId") Long productId);

    @Query("SELECT p FROM Product p where p.active=true AND p.deleted=false AND p.stockQuantity > 0 AND LOWER(p.name) LIKE LOWER(CONCAT('%',:searchKeyword,'%'))")
    List<Product> findProductByActiveTrueAndDeletedFalseAndKeyword(@Param("searchKeyword") String searchKeyword);

    @Query("SELECT p FROM Product p where p.active=true AND p.deleted=false AND p.stockQuantity > 0")
    List<Product> getProducts();
}
