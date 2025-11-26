package com.ecommerce.app.helper;

import com.ecommerce.app.constant.OrderStatus;
import com.ecommerce.app.entity.*;
import com.ecommerce.app.request.CreateItemCartRequest;
import com.ecommerce.app.response.OrderItemResponse;
import com.ecommerce.app.response.OrderResponse;
import com.ecommerce.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrdersHelper {


    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    UserService userService;

    @Autowired
    ItemCartService itemCartService;

    @Autowired
    ItemCartHelper itemCartHelper;

    @Autowired
    ProductService productService;
    public   Optional<OrderResponse> createOrder(Long userId){
        OrderResponse orderResponse = new OrderResponse();

        List<ItemCart> itemCarts = itemCartService.findByUserIdAndActiveTrueAndDeletedFalse(userId);

        User user = userService.findByIdAndActiveTrueAndDeletedFalse(userId);

        if (itemCarts.isEmpty())

            throw new RuntimeException("Item is not available in cart for this user.");
        Orders orders= createOrder(itemCarts,user,itemCarts.parallelStream().map(ItemCart::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));

        orderResponse.setOrderId(orders.getId());
        orderResponse.setOrderCreatedOn(LocalDateTime.now());
        orderResponse.setOrderStatus(orders.getOrderStatus());
        orderResponse.setTotalAmount(orders.getTotalAmount());
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        itemCarts.parallelStream().forEach(itemCart -> {
            OrderItemResponse orderItemResponse = new OrderItemResponse();
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(itemCart.getPrice());
            orderItem.setQuantity(itemCart.getQuantity());
            orderItem.setCreatedOn(LocalDateTime.now());
            orderItem.setOrders(orders);
            orderItem.setProduct(itemCart.getProduct());
            orderItemService.createOrderItem(orderItem);

            orderItemResponse.setOrderItemId(orderItem.getId());
            orderItemResponse.setQuantity(itemCart.getQuantity());
            orderItemResponse.setPrice(itemCart.getProduct().getPrice());
            orderItemResponse.setSubTotalPrice(itemCart.getPrice());
            orderResponse.setOrderItemResponses(orderItemResponses);
            orderItemResponses.add(orderItemResponse);

            removeItemCartAndReduceProductStock(userId, itemCart.getProduct().getId(), itemCart.getQuantity());


        });


        return Optional.of(orderResponse);
    }

    public void reduceProductStock(Product product, CreateItemCartRequest createItemCartRequest) {
        Integer productStockQuantity = product.getStockQuantity();
        int remainingProductInStock = productStockQuantity - createItemCartRequest.getQuantity();
        product.setStockQuantity(remainingProductInStock);
        productService.createProduct(product);
    }

    public void removeItemCartAndReduceProductStock(Long userId, Long productId, Integer quantity) {
        ItemCart itemCart = itemCartService.findByUserIdAndProductIdAndDeletedFalseAndActiveTrue(userId, productId);
        CreateItemCartRequest createItemCartRequest = new CreateItemCartRequest();
        createItemCartRequest.setUserId(userId);
        createItemCartRequest.setProductId(productId);
        createItemCartRequest.setQuantity(quantity);
        reduceProductStock(itemCart.getProduct(), createItemCartRequest);
        itemCartHelper.removeItemToCart(createItemCartRequest);
    }

    public  Orders createOrder( List<ItemCart> itemCarts ,User user,BigDecimal totalAmount){
        Orders orders = new Orders();
        orders.setOrderStatus(OrderStatus.CONFIRMED);
        orders.setUser(user);
        orders.setTotalAmount(totalAmount);
        orders.setCreatedOn(LocalDateTime.now());
        orderService.createOrder(orders);
        return  orders;
    }

}
