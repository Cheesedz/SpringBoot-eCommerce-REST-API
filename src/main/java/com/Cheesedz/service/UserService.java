package com.Cheesedz.service;

import com.Cheesedz.model.User;
import com.Cheesedz.payload.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    ResponseEntity<ResponseObject> getAllUsers();
    ResponseEntity<ResponseObject> findById(Long id);
    ResponseEntity<ResponseObject> getAllOrders(Long id);
    ResponseEntity<ResponseObject> getAllVouchers(Long id);
    ResponseEntity<ResponseObject> getAllNotifications(Long id);
    ResponseEntity<ResponseObject> getAllCartItems(Long id);
    ResponseEntity<ResponseObject> addUser(User newUser);
    ResponseEntity<ResponseObject> updateUser(User newUser, Long id);
    ResponseEntity<ResponseObject> deleteUser(Long id);
    ResponseEntity<ResponseObject> checkEmailAvailability(String email);
    ResponseEntity<ResponseObject> checkUsernameAvailability(String username);
}
