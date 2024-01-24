package com.Cheesedz.service;

import com.Cheesedz.controller.UserController;
import com.Cheesedz.model.Cart;
import com.Cheesedz.model.CartItems;
import com.Cheesedz.model.User;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemsRepository cartItemsRepository;
    @Autowired
    private CartRepository cartRepository;

    public ResponseEntity<ResponseObject> checkEmailAvailability(String email) {
        Boolean existed = userRepository.existsByEmail(email);
        return existed ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Email is valid", "")
                ):
                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                        new ResponseObject("FAILED", "Email is already taken", "")
                );
    }

    public ResponseEntity<ResponseObject> checkUsernameAvailability(String username) {
        Boolean existed = userRepository.existsByUsername(username);
        return existed ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Username is valid", "")
                ):
                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                        new ResponseObject("FAILED", "Username is already taken", "")
                );
    }

    public ResponseEntity<ResponseObject> getAllUsers() {
        List<User> foundUsers = userRepository.findAll();
        return foundUsers.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Get all users successfully", foundUsers)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("FAILED", "Cannot find any users", "")
                );
    }

    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<User> foundUsers = userRepository.findById(id);
        return foundUsers.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Get user successfully", foundUsers)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("FAILED", "Cannot find user with id = " + id, "")
                );
    }

    public ResponseEntity<ResponseObject> getAllOrders(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Get user's orders successfully",
                                orderRepository.findByUserID(id))
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("FAILED", "Cannot find user with id = " + id, "")
                );
    }

    public ResponseEntity<ResponseObject> getAllVouchers(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Get user's vouchers successfully",
                                voucherRepository.findByUserID(id))
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("FAILED", "Cannot find user with id = " + id, "")
                );
    }

    public ResponseEntity<ResponseObject> getAllNotifications(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Get user's notifications successfully",
                                notificationRepository.findByUserID(id))
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("FAILED", "Cannot find user with id = " + id, "")
                );
    }
    public ResponseEntity<ResponseObject> getAllCartItems(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            Cart foundCart = cartRepository.findByUserID(id).get(0);
            if (foundCart != null) {
                List<CartItems> foundItems = cartItemsRepository.findByCartID(foundCart.getId());
                if (foundItems.size() > 0) {
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject("OK", "Get all user's cart items successfully", foundItems)
                    );
                }
                else {
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject("OK", "There's no items in user's cart","")
                    );
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FAILED", "Cannot find user's cart","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("FAILED", "Cannot find user with id=" + id, "")
        );
    }

    public ResponseEntity<ResponseObject> addUser(User newUser) {
        List<User> foundUsers = userRepository.findByUsername(newUser.getUsername());
        if (foundUsers.size() > 0) {
            logger.info("Failed to insert data: " + newUser);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("FAILED", "Username is already existed", "")
            );
        } else {
            logger.info("Insert data successfully. " + newUser);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Add user successfully", userRepository.save(newUser))
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
                new ResponseObject("OK", "Update user successfully", updatedUser)
        );
    }

    public ResponseEntity<ResponseObject> deleteUser(Long id) {
        boolean existed = userRepository.existsById(id);
        if (existed) {
            logger.info("Delete data successfully");
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Delete user successfully", "")
            );
        } else {
            logger.info("Failed to delete data");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FAILED", "Cannot find user to delete", "")
            );
        }
    }


}
