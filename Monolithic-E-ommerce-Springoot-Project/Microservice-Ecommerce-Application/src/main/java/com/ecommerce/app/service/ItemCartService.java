package com.ecommerce.app.service;

import com.ecommerce.app.entity.ItemCart;

import java.util.List;

public interface ItemCartService {
    ItemCart findByUserIdAndProductIdAndActiveTrueAndDeletedFalse(Long userId, Long productId);

    void addItemTocCart(ItemCart itemCart);

    void deleteItemCart(Long itemCartId);


    ItemCart findByUserIdAndProductIdAndDeletedTrueAndActiveFalse(Long userId, Long productId);

    List<ItemCart> findByUserIdAndActiveTrueAndDeletedFalse(Long userId);

    ItemCart findByUserIdAndProductIdAndDeletedFalseAndActiveTrue(Long userId, Long productId);
}
