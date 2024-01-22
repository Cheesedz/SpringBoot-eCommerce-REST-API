package com.Cheesedz.service;

import com.Cheesedz.controller.OrderController;
import com.Cheesedz.controller.ProductController;
import com.Cheesedz.model.Order;
import com.Cheesedz.model.Product;
import com.Cheesedz.model.Voucher;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.repository.OrderRepository;
import com.Cheesedz.repository.ProductRepository;
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
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ResponseObject> getAllOrders(Long userID) {
        List<Order> foundOrders = orderRepository.findByUserID(userID);
        return foundOrders.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get all orders successfully", foundOrders)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find any orders", "")
                );
    }

    public ResponseEntity<ResponseObject> findById(Long id, Long userID) {
        Order order = orderRepository.findById(id).get();
        return order.getUserID().equals(userID) ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get order successfully", order)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Order doesn't belong to user", "")
                );
    }

    public ResponseEntity<ResponseObject> getAllProducts(Long id, Long userID) {
        Order order = orderRepository.findById(id).get();
        return order.getUserID().equals(userID) ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get order successfully", productRepository.findByOrderID(id))
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Order doesn't belong to user", "")
                );
    }

    public ResponseEntity<ResponseObject> insertOrder(Order newOrder, Long userID) {
        Optional<Order> foundOrders = orderRepository.findById(newOrder.getOrderNumber());
        if (foundOrders.isPresent()) {
            logger.info("Failed to insert data: " + newOrder);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("failed", "Order id already existed", "")
            );
        } else {
            newOrder.setUserID(userID);
            logger.info("Insert data successfully. " + newOrder);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Add order successfully", orderRepository.save(newOrder))
            );
        }
    }

    public ResponseEntity<ResponseObject> updateOrder(Order newOrder, Long id, Long userID) {
        Order foundOrder = orderRepository.findById(id).get();
        if (foundOrder.getUserID().equals(userID)) {
            foundOrder.setUserID(newOrder.getUserID());
            foundOrder.setVoucherID(newOrder.getVoucherID());
            foundOrder.setOrderDate(newOrder.getOrderDate());
            foundOrder.setExpectedShippedDate(newOrder.getExpectedShippedDate());
            foundOrder.setTypeOfPayment(newOrder.getTypeOfPayment());
            foundOrder.setStatus(newOrder.getStatus());
            orderRepository.save(foundOrder);
            logger.info("Update data successfully. " + newOrder);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update order successfully", foundOrder)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                new ResponseObject("failed", "Order doesn't not belong to user ", "")
        );
//        Order updatedOrder = orderRepository.findById(id).map(
//                order -> {
//                    order.setUserID(newOrder.getUserID());
//                    order.setVoucherID(newOrder.getVoucherID());
//                    order.setOrderDate(newOrder.getOrderDate());
//                    order.setExpectedShippedDate(newOrder.getExpectedShippedDate());
//                    order.setTypeOfPayment(newOrder.getTypeOfPayment());
//                    order.setStatus(newOrder.getStatus());
//                    return orderRepository.save(order);
//                }
//        ).orElseGet(()-> orderRepository.save(newOrder));
//        logger.info("Update data successfully. " + newOrder);
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok", "Update order successfully", updatedOrder)
//        );
    }

    public ResponseEntity<ResponseObject> deleteOrder(Long id, Long userID) {
        Order foundOrder = orderRepository.findById(id).get();
        if (foundOrder.getUserID().equals(userID)) {
            logger.info("Delete data successfully");
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete order successfully", "")
            );
        } else {
            logger.info("Failed to delete data");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Order doesn't not belong to user", "")
            );
        }
    }
}
