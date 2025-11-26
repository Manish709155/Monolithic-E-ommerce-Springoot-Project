package com.ecommerce.app.service;

import com.ecommerce.app.entity.User;
import com.ecommerce.app.request.UserUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(User user);

    List<User> getUserList();

    User findByIdAndActiveTrueAndDeletedFalse(Long userId);
    // or
    Optional<User> fetchSingleUserByUserId(Long userId);

    boolean updateUser(Long userId , UserUpdateRequest userUpdateRequest);

}
