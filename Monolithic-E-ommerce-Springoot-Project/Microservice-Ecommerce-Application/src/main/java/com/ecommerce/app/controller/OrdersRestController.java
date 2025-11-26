package com.ecommerce.app.controller;

import com.ecommerce.app.helper.OrdersHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrdersRestController {

    @Autowired
    OrdersHelper ordersHelper;

    @PostMapping("/create{userId}")
    public ResponseEntity<Object> createProduct(@PathVariable("userId") Long userId){
        try{
            return new ResponseEntity<>(ordersHelper.createOrder(userId), HttpStatus.CREATED);
        }
        catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
