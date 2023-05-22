package com.example.weddingofhuongsan.service;

import com.example.weddingofhuongsan.entity.Product;

public interface ProductService {
    Double totalAmountOfProduct();

    Product addProduct(Product product);
}
