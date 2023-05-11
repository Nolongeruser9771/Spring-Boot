package com.example.shoppingcart.service;

import com.example.shoppingcart.dto.CartItemDto;
import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.exception.BadRequestException;
import com.example.shoppingcart.exception.ResourceNotFoundException;
import com.example.shoppingcart.mapper.CartMapper;
import com.example.shoppingcart.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    private CartRepository cartRepository;
    public CartServiceImpl(CartRepository cartRepository){
        this.cartRepository =cartRepository;
    }

    @Override
    public List<CartItemDto> getAllCartItems() {
        return cartRepository.getAllCartItems().stream().map(CartMapper::cartItemDto).toList();
    }

    private CartItem getCartItemFromRepoById(Integer id){
        return cartRepository.getCardItemById(id).orElseThrow(() -> new ResourceNotFoundException("Cart id = "+ id + " is not found"));
    }

    @Override
    public void deleteCartItem(Integer id) {
        boolean isDeleted = cartRepository.deleteCartItem(id);
        if (!isDeleted) throw new ResourceNotFoundException("Cart id "+ id +" is not found");
    }

    @Override
    public void increaseItemCount(Integer id) {
        CartItem cartItem = getCartItemFromRepoById(id);
        cartItem.setCount(cartItem.getCount()+1);
    }

    @Override
    public void decreaseItemCount(Integer id) {
        CartItem cartItem = getCartItemFromRepoById(id);
        if (cartItem.getCount()==1) {
           throw new BadRequestException("count should not less than 1");
        }
        cartItem.setCount(cartItem.getCount() - 1);
    }
}
