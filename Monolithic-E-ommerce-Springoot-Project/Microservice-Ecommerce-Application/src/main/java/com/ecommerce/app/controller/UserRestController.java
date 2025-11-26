package com.ecommerce.app.controller;
import com.ecommerce.app.helper.UserHelper;
import com.ecommerce.app.request.UserRequest;
import com.ecommerce.app.request.UserUpdateRequest;
import com.ecommerce.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/user")
public class UserRestController  {

    @Autowired
    UserHelper userHelper;

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserRequest userRequest){
        try{
            return new ResponseEntity<>(userHelper.createUser(userRequest), HttpStatus.CREATED);
        }
        catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getUserList(){
        try{
            return new ResponseEntity<>(userHelper.getCompanyList(), HttpStatus.OK);
        }
        catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById{userId}")
    public ResponseEntity<Object> getUser(@PathVariable("userId") Long userId){
        try{
            return new ResponseEntity<>(userHelper.getUser(userId), HttpStatus.OK);
        }
        catch (RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //or try in another way

    @GetMapping("/fetchSingleUserByUserId{id}")
    public ResponseEntity<Object> fetchSingleByUserId(@PathVariable("id") Long id){
        return userHelper.fetchSingleUserByUserId(id).<ResponseEntity<Object>>map(
                response ->
                        new ResponseEntity<>(response, HttpStatus.NOT_FOUND))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable("userId") Long userId , @RequestBody UserUpdateRequest userUpdateRequest){
       boolean userUpdated= userService.updateUser(userId,userUpdateRequest);
       if (userUpdated){
           return  ResponseEntity.ok("User has been updated successfully.");
       }
        return ResponseEntity.notFound().build();
    }
}
