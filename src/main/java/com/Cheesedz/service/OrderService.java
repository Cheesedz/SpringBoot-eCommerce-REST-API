package com.Cheesedz.service;

import com.Cheesedz.controller.ProductController;
import com.Cheesedz.model.Order;
import com.Cheesedz.model.Product;
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
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ResponseObject> getAllOrders() {
        List<Order> foundOrders = orderRepository.findAll();
        return foundOrders.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get all orders successfully", foundOrders)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find any orders", "")
                );
    }

    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<Order> foundOrders = orderRepository.findById(id);
        return foundOrders.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query order successfully", foundOrders)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find order with id = " + id, "")
                );
    }

    public ResponseEntity<ResponseObject> findAllProducts(Long id) {
        List<Object> responses = new ArrayList<>();
        Optional<Order> foundOrder = orderRepository.findById(id);
        return foundOrder.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query order's products successfully",
                            productRepository.findByOrderID(id))
            ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find order with id = " + id, "")
        );
    }

    public ResponseEntity<ResponseObject> insertOrder(Order newOrder) {
        Optional<Order> foundOrders = orderRepository.findById(newOrder.getOrderNumber());
        if (!foundOrders.isPresent()) {
            logger.info("Failed to insert data: " + newOrder);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("failed", "Order id already existed", "")
            );
        } else {
            logger.info("Insert data successfully. " + newOrder);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert order successfully", orderRepository.save(newOrder))
            );
        }
    }

    public ResponseEntity<ResponseObject> updateOrder(Order newOrder, Long id) {
        Order updatedOrder = orderRepository.findById(id).map(
                order -> {
                    order.setUserID(newOrder.getUserID());
                    order.setVoucherID(newOrder.getVoucherID());
                    order.setOrderDate(newOrder.getOrderDate());
                    order.setExpectedShippedDate(newOrder.getExpectedShippedDate());
                    order.setTypeOfPayment(newOrder.getTypeOfPayment());
                    order.setStatus(newOrder.getStatus());
                    return orderRepository.save(order);
                }
        ).orElseGet(()-> orderRepository.save(newOrder));
        logger.info("Update data successfully. " + newOrder);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update order successfully", updatedOrder)
        );
    }

    public ResponseEntity<ResponseObject> deleteOrder(Long id) {
        boolean existed = orderRepository.existsById(id);
        if (existed) {
            logger.info("Delete data successfully");
            orderRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete order successfully", "")
            );
        } else {
            logger.info("Failed to delete data");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find order to delete", "")
            );
        }
    }
}
