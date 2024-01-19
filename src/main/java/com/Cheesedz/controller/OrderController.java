package com.Cheesedz.controller;

import com.Cheesedz.model.Order;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<ResponseObject> findAllProducts(@PathVariable Long id) {
        return orderService.findAllProducts(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertOrder(@RequestBody Order newProduct) {
        return orderService.insertOrder(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateOrder(@RequestBody Order newOrder, @PathVariable Long id) {
        return orderService.updateOrder(newOrder, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteOrder(@PathVariable Long id) {
        return orderService.deleteOrder(id);
    }
}
