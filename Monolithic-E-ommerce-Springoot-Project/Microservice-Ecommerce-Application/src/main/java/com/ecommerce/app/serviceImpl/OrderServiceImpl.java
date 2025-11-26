package com.ecommerce.app.serviceImpl;

import com.ecommerce.app.entity.Orders;
import com.ecommerce.app.repository.OrderRepository;
import com.ecommerce.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private  final OrderRepository orderRepository;


    @Override
    public void createOrder(Orders orders) {
        orderRepository.save(orders);
    }
}
