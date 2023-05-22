package com.example.weddingofhuongsan;

import com.example.weddingofhuongsan.entity.Customer;
import com.example.weddingofhuongsan.entity.Gift;
import com.example.weddingofhuongsan.entity.Menu;
import com.example.weddingofhuongsan.entity.Product;
import com.example.weddingofhuongsan.repository.CustomerRepository;
import com.example.weddingofhuongsan.repository.GiftRepository;
import com.example.weddingofhuongsan.repository.MenuRepository;
import com.example.weddingofhuongsan.repository.ProductRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepoTest {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    GiftRepository giftRepository;

    @Test
    @Rollback(value = false)
    void saveCustomer(){
        Faker faker = new Faker();
        List<Customer> customers = new ArrayList<>();
        for (int i = 1; i <= 600; i++) {
            Customer customer = Customer.builder()
                    .name(faker.name().fullName())
                    .phone(faker.phoneNumber().cellPhone())
                    .address(faker.address().city())
                    .build();
            customers.add(customer);
        }
        customerRepository.saveAll(customers);
    }

    @Test
    @Rollback(value = false)
    void saveProduct(){
        Faker faker = new Faker();
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Product product = Product.builder()
                    .name(faker.food().ingredient())
                    .price(faker.number().numberBetween(100000,10000000))
                    .quantity(faker.number().numberBetween(1,100))
                    .unit("VND")
                    .type("rent")
                    .build();
            products.add(product);
        }
        productRepository.saveAll(products);
    }

    @Test
    @Rollback(value = false)
    void saveMenu(){
        Faker faker = new Faker();
        List<Menu> menus = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Menu menu = Menu.builder()
                            .name(faker.food().ingredient())
                            .price(faker.number().numberBetween(100000,1000000))
                            .build();
            menus.add(menu);
        }
        menuRepository.saveAll(menus);
    }

    @Test
    @Rollback(value = false)
    void saveGilf(){
        Faker faker = new Faker();
        List<Gift> gifts = new ArrayList<>();
        for (int i = 1; i <= 600; i++) {
            Gift gift = Gift.builder()
                    .customerId(i)
                    .price(faker.random().nextInt(100000,1000000))
                    .build();
            gifts.add(gift);
        }
        giftRepository.saveAll(gifts);
    }
}
