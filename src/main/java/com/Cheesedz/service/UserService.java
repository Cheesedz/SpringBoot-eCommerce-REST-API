package com.Cheesedz.service;

import com.Cheesedz.controller.ProductController;
import com.Cheesedz.controller.UserController;
import com.Cheesedz.model.User;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.repository.OrderRepository;
import com.Cheesedz.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<ResponseObject> getAllUsers() {
        List<User> foundUsers = userRepository.findAll();
        return foundUsers.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get all users successfully", foundUsers)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find any users", "")
                );
    }

    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<User> foundUsers = userRepository.findById(id);
        return foundUsers.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query order successfully", foundUsers)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find user with id = " + id, "")
                );
    }

    public ResponseEntity<ResponseObject> findAllOrders(Long id) {
        List<Object> responses = new ArrayList<>();
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query user's orders successfully",
                                orderRepository.findByUserID(id))
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find user with id = " + id, "")
                );
    }

    public ResponseEntity<ResponseObject> insertUser(User newUser) {
        List<User> foundUsers = userRepository.findByUsername(newUser.getUsername());
        if (foundUsers.size() > 0) {
            logger.info("Failed to insert data: " + newUser);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("failed", "Username already existed", "")
            );
        } else {
            logger.info("Insert data successfully. " + newUser);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert user successfully", userRepository.save(newUser))
            );
        }
    }

    public ResponseEntity<ResponseObject> updateUser(User newUser, Long id) {
        User updatedUser = userRepository.findById(id).map(
                user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setPhone(newUser.getPhone());
                    user.setGender(newUser.getGender());
                    user.setDob(newUser.getDob());
                    return userRepository.save(user);
                }
        ).orElseGet(()-> userRepository.save(newUser));
        logger.info("Update data successfully. " + newUser);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update user successfully", updatedUser)
        );
    }

    public ResponseEntity<ResponseObject> deleteUser(Long id) {
        boolean existed = userRepository.existsById(id);
        if (existed) {
            logger.info("Delete data successfully");
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete user successfully", "")
            );
        } else {
            logger.info("Failed to delete data");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find user to delete", "")
            );
        }
    }
}
