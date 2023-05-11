package com.example.shoppingcart.repository.db;

import com.example.shoppingcart.entity.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartDB {
    public static List<CartItem> cartItems = new ArrayList<>(List.of(
            CartItem.builder().withCount(2).withCourseId(1).withId(1).build(),
            CartItem.builder().withCount(3).withCourseId(2).withId(2).build(),
            CartItem.builder().withCount(1).withCourseId(3).withId(3).build()
    ));
}
