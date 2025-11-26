package com.ecommerce.app.controller;
import com.ecommerce.app.helper.ProductHelper;
import com.ecommerce.app.request.ProductCreateRequest;
import com.ecommerce.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/product")
public class ProductRestController  {

    @Autowired
    ProductHelper productHelper;

    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@RequestBody ProductCreateRequest productCreateRequest){
        try{
            return new ResponseEntity<>(productHelper.createProduct(productCreateRequest), HttpStatus.CREATED);
        }
        catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getProducts(){
        try{
            return new ResponseEntity<>(productHelper.getProducts(), HttpStatus.OK);
        }
        catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById{productId}")
    public ResponseEntity<Object> getProduct(@PathVariable("productId") Long productId){
        try{
            return new ResponseEntity<>(productHelper.getProduct(productId), HttpStatus.OK);
        }
        catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search{searchKeyword}")
    public ResponseEntity<Object> searchProductByKeyword(@PathVariable("searchKeyword") String searchKeyword){
        try{
            return new ResponseEntity<>(productHelper.searchProductByKeyword(searchKeyword), HttpStatus.OK);
        }
        catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update{productId}")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductCreateRequest productCreateRequest,@PathVariable("productId") Long productId){
        try{
            return new ResponseEntity<>(productHelper.updateProduct(productCreateRequest,productId), HttpStatus.OK);
        }
        catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
