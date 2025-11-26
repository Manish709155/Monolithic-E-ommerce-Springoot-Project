package com.ecommerce.app.repository;

import com.ecommerce.app.entity.ItemCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCartRepository extends JpaRepository<ItemCart, Long> {

    ItemCart findByUserIdAndProductIdAndActiveTrueAndDeletedFalse(Long userId,Long productId);

    ItemCart findByUserIdAndProductIdAndDeletedTrueAndActiveFalse(Long userId, Long productId);

    List<ItemCart> findByUserIdAndActiveTrueAndDeletedFalse(Long userId);

    ItemCart findByUserIdAndProductIdAndDeletedFalseAndActiveTrue(Long userId, Long productId);
}
