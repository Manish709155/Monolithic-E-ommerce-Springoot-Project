package com.ecommerce.app.serviceImpl;

import com.ecommerce.app.entity.User;
import com.ecommerce.app.repository.UserRepository;
import com.ecommerce.app.request.UserUpdateRequest;
import com.ecommerce.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User findByIdAndActiveTrueAndDeletedFalse(Long userId) {
        return userRepository.findByIdAndActiveTrueAndDeletedFalse(userId);
    }

    @Override
    public Optional<User> fetchSingleUserByUserId(Long userId) {
       return  userRepository.findById(userId).filter(user->user.getId().equals(userId)).stream().findFirst();
    }

    @Override
    public boolean updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
      return   userRepository.findAll()
                .stream().filter(user->user.getId().equals(userId))
                .findFirst().map(existingUser->{
                    existingUser.setEmailId(userUpdateRequest.getEmailId());
                    existingUser.setMobileNo(userUpdateRequest.getMobileNo());
                    existingUser.setPassword(userUpdateRequest.getPassword());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }
}
