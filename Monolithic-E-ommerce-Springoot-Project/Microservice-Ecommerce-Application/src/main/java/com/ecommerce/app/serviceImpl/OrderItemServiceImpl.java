package com.ecommerce.app.serviceImpl;

import com.ecommerce.app.entity.OrderItem;
import com.ecommerce.app.repository.OrderItemRepository;
import com.ecommerce.app.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private  final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public void createOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
