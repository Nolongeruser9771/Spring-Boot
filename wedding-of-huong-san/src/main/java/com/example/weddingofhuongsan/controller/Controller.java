package com.example.weddingofhuongsan.controller;

import com.example.weddingofhuongsan.dto.WeddingSpendResponse;
import com.example.weddingofhuongsan.entity.Customer;
import com.example.weddingofhuongsan.entity.Gift;
import com.example.weddingofhuongsan.entity.Menu;
import com.example.weddingofhuongsan.entity.Product;
import com.example.weddingofhuongsan.service.CustomerService;
import com.example.weddingofhuongsan.service.GiftService;
import com.example.weddingofhuongsan.service.MenuService;
import com.example.weddingofhuongsan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @Autowired
    MenuService menuService;

    @Autowired
    GiftService giftService;

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody Product newProduct) {
        return ResponseEntity.ok().body(productService.addProduct(newProduct));
    }

    @PostMapping("/add-customer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer) {
        return ResponseEntity.ok().body(customerService.addCustomer(newCustomer));
    }

    @PostMapping("/add-menu")
    public ResponseEntity<?> addMenu(@RequestBody Menu newMenu) {
        return ResponseEntity.ok().body(menuService.addMenu(newMenu));
    }

    @PostMapping("/add-gift")
    public ResponseEntity<?> addGift(@RequestBody Gift gift) {
        return ResponseEntity.ok().body(giftService.addGift(gift));
    }

    @GetMapping("/wedding-operation")
    public ResponseEntity<?> weddingOperate() {
        return ResponseEntity.ok().body(WeddingSpendResponse.builder()
                .productSpend(productService.totalAmountOfProduct())
                .MenuSpend(menuService.totalAmountPerTable()*100)
                .totalGift(giftService.totalAmountOfGift())
                .build()
        );
    }
}
