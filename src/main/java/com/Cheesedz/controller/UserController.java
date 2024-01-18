package com.Cheesedz.controller;

import com.Cheesedz.model.User;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<ResponseObject> findAllOrders(@PathVariable Long id) {
        return userService.findAllOrders(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertOrder(@RequestBody User newUser) {
        return userService.insertUser(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateOrder(@RequestBody User newUser, @PathVariable Long id) {
        return userService.updateUser(newUser, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteOrder(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
