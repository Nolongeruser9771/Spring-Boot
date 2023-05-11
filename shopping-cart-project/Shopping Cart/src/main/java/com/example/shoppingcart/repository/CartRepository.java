package com.example.shoppingcart.repository;

import com.example.shoppingcart.entity.CartItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.shoppingcart.repository.db.CartDB.cartItems;

@Repository
public class CartRepository {
    public List<CartItem> getAllCartItems(){return cartItems;}

    public Optional<CartItem> getCardItemById(Integer id){
        return cartItems.stream().filter(cartItem -> cartItem.getId() == id).findFirst();
    }

    public boolean deleteCartItem(Integer id){
        CartItem cartItem2Delete = null;
        for (CartItem cartItem: cartItems) {
            if (cartItem.getId() == id) {
                cartItem2Delete = cartItem;
                break;
            }
        }
        if (cartItem2Delete != null) {
            cartItems.remove(cartItem2Delete);
            return true;
        }
        return false;
    }
}
