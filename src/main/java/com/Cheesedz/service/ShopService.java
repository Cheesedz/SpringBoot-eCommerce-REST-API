package com.Cheesedz.service;

import com.Cheesedz.model.Shop;
import com.Cheesedz.payload.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface ShopService {
    ResponseEntity<ResponseObject> getAllShops();
    ResponseEntity<ResponseObject> findById(Long id);
    ResponseEntity<ResponseObject> findAllProducts(Long id);
    ResponseEntity<ResponseObject> addShop(Shop newShop);
    ResponseEntity<ResponseObject> updateShop(Shop newShop, Long id);
    ResponseEntity<ResponseObject> deleteShop(Long id);
}
