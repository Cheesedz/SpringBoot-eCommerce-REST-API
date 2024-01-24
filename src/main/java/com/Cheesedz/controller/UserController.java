package com.Cheesedz.controller;

import com.Cheesedz.model.User;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> getUser(@PathVariable(name = "id") Long id) {
        return userService.findById(id);
    }

    @GetMapping("/checkUsernameAvailability")
    public ResponseEntity<ResponseObject> checkUsernameAvailability(@RequestParam(value = "username") String username) {
        return userService.checkUsernameAvailability(username);
    }

    @GetMapping("/checkEmailAvailability")
    public ResponseEntity<ResponseObject> checkEmailAvailability(@RequestParam(value = "email") String email) {
        return userService.checkEmailAvailability(email);
    }

    @GetMapping("/{id}/orders")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseObject> getAllOrders(@PathVariable(name = "id") Long id) {
        return userService.getAllOrders(id);
    }

    @GetMapping("{id}/cart")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseObject> getAllCartItems(@PathVariable(name = "id") Long id) {
        return userService.getAllCartItems(id);
    }

    @GetMapping("/{id}/vouchers")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseObject> getAllVouchers(@PathVariable(name = "id") Long id) {
        return userService.getAllVouchers(id);
    }

    @GetMapping("/{id}/notifications")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseObject> getAllNotifications(@PathVariable(name = "id") Long id) {
        return userService.getAllNotifications(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> addUser(@RequestBody User newUser) {
        return userService.addUser(newUser);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser, @PathVariable(name = "id") Long id) {
        return userService.updateUser(newUser, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable(name = "id") Long id) {
        return userService.deleteUser(id);
    }
}
