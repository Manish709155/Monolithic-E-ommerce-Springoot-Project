package com.ecommerce.app.controller;

import com.ecommerce.app.request.CreateItemCartRequest;
import com.ecommerce.app.helper.ItemCartHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/itemCart")
@RestController
@RequiredArgsConstructor

@Slf4j
public class ItemCartRestController {

    private  final  ItemCartHelper itemCartHelper;

    @PostMapping("/addItemToCart")
    public ResponseEntity<Object> addItemToCart(@RequestBody CreateItemCartRequest createItemCartRequest){
        try{
            return new ResponseEntity<>(itemCartHelper.addItemToCart(createItemCartRequest), HttpStatus.CREATED);
        }
        catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/removeItemToCart")
    public ResponseEntity<Object> removeItemToCart(@RequestBody CreateItemCartRequest createItemCartRequest){
        try{
            return new ResponseEntity<>(itemCartHelper.removeItemToCart(createItemCartRequest), HttpStatus.OK);
        }
        catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
