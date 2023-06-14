package com.example.userapp.repository;

import com.example.userapp.entity.Customer;
import com.example.userapp.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}