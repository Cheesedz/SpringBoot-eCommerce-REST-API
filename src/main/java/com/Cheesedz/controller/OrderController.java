package com.Cheesedz.controller;

import com.Cheesedz.model.Order;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getOrder(@PathVariable(name = "id") Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<ResponseObject> getAllProducts(@PathVariable(name = "id") Long id) {
        return orderService.findAllProducts(id);
    }

    @PostMapping("/insert")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseObject> insertOrder(@RequestBody Order newProduct) {
        return orderService.insertOrder(newProduct);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseObject> updateOrder(@RequestBody Order newOrder, @PathVariable(name = "id") Long id) {
        return orderService.updateOrder(newOrder, id);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseObject> deleteOrder(@PathVariable(name = "id") Long id) {
        return orderService.deleteOrder(id);
    }
}
