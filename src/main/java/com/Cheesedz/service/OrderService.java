package com.Cheesedz.service;

import com.Cheesedz.model.Order;
import com.Cheesedz.payload.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<ResponseObject> getAllOrders(Long userID);
    ResponseEntity<ResponseObject> findById(Long id, Long userID);
    ResponseEntity<ResponseObject> getAllProducts(Long id, Long userID);
    ResponseEntity<ResponseObject> addOrder(Order newOrder, Long userID);
    ResponseEntity<ResponseObject> updateOrder(Order newOrder, Long id, Long userID);
    ResponseEntity<ResponseObject> deleteOrder(Long id, Long userID);
}
