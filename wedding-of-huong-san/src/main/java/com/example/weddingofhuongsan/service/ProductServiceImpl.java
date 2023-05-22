package com.example.weddingofhuongsan.service;


import com.example.weddingofhuongsan.entity.Product;
import com.example.weddingofhuongsan.repository.ProductRepository;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    //Chi phi san pham
    @Override
    @NumberFormat(pattern = "000,000,000.00")
    public Double totalAmountOfProduct() {
        List<Product> products = productRepository.findAll();
        double totalAmount = 0d;
        for (Product product:products){
            totalAmount+=product.getPrice()*product.getQuantity();
        }
        return totalAmount;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
