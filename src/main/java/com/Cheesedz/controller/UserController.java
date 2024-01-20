package com.Cheesedz.controller;

import com.Cheesedz.model.User;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseObject> getUser(@PathVariable(name = "id") Long id) {
        return userService.findById(id);
    }

    @GetMapping("/{id}/orders")
    //@PreAuthorize("hasRole('USER') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> findAlOrders(@PathVariable(name = "id") Long id) {
        return userService.findAllOrders(id);
    }

    @GetMapping("/{id}/vouchers")
    //@PreAuthorize("hasRole('USER') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> findAlVouchers(@PathVariable(name = "id") Long id) {
        return userService.findAllOrders(id);
    }

    @PostMapping("/insert")
    //@PreAuthorize("hasRole('USER') or hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser) {
        return userService.insertUser(newUser);
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
