package com.example.shoppingcart.service;

import com.example.shoppingcart.dto.CartItemDto;

import java.util.List;

public interface CartService {
    List<CartItemDto> getAllCartItems();

    void deleteCartItem(Integer id);

    void increaseItemCount(Integer id);

    void decreaseItemCount(Integer id);
}
