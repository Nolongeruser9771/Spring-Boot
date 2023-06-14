package com.example.userapp;

import com.example.userapp.entity.Customer;
import com.example.userapp.entity.Order;
import com.example.userapp.entity.User;
import com.example.userapp.repository.CustomerRepository;
import com.example.userapp.repository.OrderRepository;
import com.example.userapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserAppApplicationTests {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void save_customer() {
        for (int i = 0; i < 2; i++) {
            Customer customer = Customer.builder()
                    .name("customer "+ (i+1))
                    .build();

            customerRepository.save(customer);

            for (int j = 0; j < 3; j++) {
                Order order = Order.builder()
                        .orderNumber(j+1)
                        .customer(customer)
                        .build();

                orderRepository.save(order);
            }
        }
    }

    @Test
    void delete_customer() {
        customerRepository.deleteById(1);
    }

    @Test
    void add_customer(){
        List<Order> orderList = new ArrayList<>();

        Customer customer = Customer.builder()
                .name("customer Z")
                .build();
        customerRepository.save(customer);

        for (int j = 0; j < 3; j++) {
            Order order = Order.builder()
                    .orderNumber(j + 1)
                    .customer(customer)
                    .build();
            orderList.add(order);
        }
        orderRepository.saveAll(orderList);
    }

    @Test
    @Transactional
    void demo_removal(){
        Customer customer = customerRepository.findById(3)
                .orElse(null);
        assert customer != null;
        customer.getOrders().remove(0);
        customerRepository.save(customer);
    }

    @Test
    @Transactional
    void demo_fetch_type(){
        Customer customer = customerRepository.findById(3)
                .orElse(null);
        System.out.println("-------");
        assert customer != null;
        System.out.println(customer.getOrders().get(0));
    }

    @Test
    void save_users(){
        for (int i = 0; i < 3; i++) {
            User user = User.builder()
                    .name("user "+ (i+1))
                    .build();
            userRepository.save(user);
        }
    }
}
