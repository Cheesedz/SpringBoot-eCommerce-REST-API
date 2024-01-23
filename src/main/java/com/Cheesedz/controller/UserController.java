package com.Cheesedz.controller;

import com.Cheesedz.model.User;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getUser(@PathVariable(name = "id") Long id) {
        return userService.findById(id);
    }

    @GetMapping("/{id}/orders")
    //@PreAuthorize("hasRole('USER') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> getAllOrders(@PathVariable(name = "id") Long id) {
        return userService.getAllOrders(id);
    }

    @GetMapping("/{id}/vouchers")
    //@PreAuthorize("hasRole('USER') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> findAllVouchers(@PathVariable(name = "id") Long id) {
        return userService.getAllVouchers(id);
    }

    @GetMapping("/{id}/notifications")
    //@PreAuthorize("hasRole('USER') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> getAllNotifications(@PathVariable(name = "id") Long id) {
        return userService.getAllNotifications(id);
    }

    @PostMapping("/insert")
    //@PreAuthorize("hasRole('USER') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser) {
        return userService.addUser(newUser);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser, @PathVariable(name = "id") Long id) {
        return userService.updateUser(newUser, id);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable(name = "id") Long id) {
        return userService.deleteUser(id);
    }
}
