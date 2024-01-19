package com.Cheesedz.service;

import com.Cheesedz.controller.ProductController;
import com.Cheesedz.controller.ShopController;
import com.Cheesedz.model.Order;
import com.Cheesedz.model.Product;
import com.Cheesedz.model.Shop;
import com.Cheesedz.payload.ResponseObject;
import com.Cheesedz.repository.OrderRepository;
import com.Cheesedz.repository.ProductRepository;
import com.Cheesedz.repository.ShopRepository;
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
public class ShopService {
    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ResponseObject> getAllShops() {
        List<Shop> foundShops = shopRepository.findAll();
        return foundShops.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get all shops successfully", foundShops)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find any shops", "")
                );
    }

    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<Shop> foundShops = shopRepository.findById(id);
        return foundShops.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query shop successfully", foundShops)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find shop with id = " + id, "")
                );
    }

    public ResponseEntity<ResponseObject> findAllProducts(Long id) {
        List<Object> responses = new ArrayList<>();
        Optional<Shop> foundOrder = shopRepository.findById(id);
        return foundOrder.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query shop's products successfully",
                                productRepository.findByShopID(id))
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find shop with id = " + id, "")
                );
    }

    public ResponseEntity<ResponseObject> insertShop(Shop newShop) {
        Optional<Shop> foundShops = shopRepository.findById(newShop.getShopID());
        if (!foundShops.isPresent()) {
            logger.info("Failed to insert data: " + newShop);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("failed", "Shop id already existed", "")
            );
        } else {
            logger.info("Insert data successfully. " + newShop);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert shop successfully", shopRepository.save(newShop))
            );
        }
    }

    public ResponseEntity<ResponseObject> updateShop(Shop newShop, Long id) {
        Shop updatedShop = shopRepository.findById(id).map(
                shop -> {
                    shop.setName(newShop.getName());
                    shop.setTypeOfProduct(newShop.getTypeOfProduct());
                    shop.setTypeOfShop(newShop.getTypeOfShop());
                    shop.setDescription(newShop.getDescription());
                    shop.setFollowing(newShop.getFollowing());
                    shop.setFollowers(newShop.getFollowers());
                    shop.setChatPerformance(newShop.getChatPerformance());
                    shop.setJoiningDate(newShop.getJoiningDate());
                    return shopRepository.save(shop);
                }
        ).orElseGet(()-> shopRepository.save(newShop));
        logger.info("Update data successfully. " + newShop);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update shop successfully", updatedShop)
        );
    }

    public ResponseEntity<ResponseObject> deleteShop(Long id) {
        boolean existed = shopRepository.existsById(id);
        if (existed) {
            logger.info("Delete data successfully");
            shopRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete shop successfully", "")
            );
        } else {
            logger.info("Failed to delete data");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find shop to delete", "")
            );
        }
    }
}
