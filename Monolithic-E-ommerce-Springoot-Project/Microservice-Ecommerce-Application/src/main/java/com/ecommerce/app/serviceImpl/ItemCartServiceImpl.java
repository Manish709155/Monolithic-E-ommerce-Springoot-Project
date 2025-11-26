package com.ecommerce.app.serviceImpl;

import com.ecommerce.app.entity.ItemCart;
import com.ecommerce.app.repository.ItemCartRepository;
import com.ecommerce.app.service.ItemCartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCartServiceImpl implements ItemCartService {

    ItemCartRepository itemCartRepository;

    public ItemCartServiceImpl(ItemCartRepository itemCartRepository) {
        this.itemCartRepository = itemCartRepository;
    }

    @Override
    public ItemCart findByUserIdAndProductIdAndActiveTrueAndDeletedFalse(Long userId, Long productId) {
        return itemCartRepository.findByUserIdAndProductIdAndActiveTrueAndDeletedFalse(userId,productId);
    }

    @Override
    public void addItemTocCart(ItemCart itemCart) {
        itemCartRepository.save(itemCart);
    }

    @Override
    public void deleteItemCart(Long itemCartId) {
        itemCartRepository.deleteById(itemCartId);
    }

    @Override
    public ItemCart findByUserIdAndProductIdAndDeletedTrueAndActiveFalse(Long userId, Long productId) {
        return itemCartRepository.findByUserIdAndProductIdAndDeletedTrueAndActiveFalse(userId,productId);
    }

    @Override
    public List<ItemCart> findByUserIdAndActiveTrueAndDeletedFalse(Long userId) {
        return itemCartRepository.findByUserIdAndActiveTrueAndDeletedFalse(userId);
    }

    @Override
    public ItemCart findByUserIdAndProductIdAndDeletedFalseAndActiveTrue(Long userId, Long productId) {
        return itemCartRepository.findByUserIdAndProductIdAndDeletedFalseAndActiveTrue(userId,productId);
    }

}
