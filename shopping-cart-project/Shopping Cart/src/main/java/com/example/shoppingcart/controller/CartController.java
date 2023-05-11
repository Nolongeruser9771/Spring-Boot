package com.example.shoppingcart.controller;

import com.example.shoppingcart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cartItems")
@Validated
public class CartController {
    private CartService cartService;
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    //1. Lấy danh sách tất cả sản phẩm trong giỏ hàng (GET : /api/v1/cartItems)
    @GetMapping("")
    public ResponseEntity<?> getAllCartItems(){
        return ResponseEntity.ok().body(cartService.getAllCartItems());
    }

    //2. Xóa sản phẩm trong giỏ hàng (DELETE : /api/v1/cartItems/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCartItem(@PathVariable("id") Integer id){
        cartService.deleteCartItem(id);
        return ResponseEntity.ok().body("Cart item id "+ id + " is successfully deleted!");
    }

    //3. Tăng số lượng sản phẩm trong giỏ hàng (PUT : /api/v1/cartItems/{id}/increment)
    @PutMapping("/{id}/increment")
    public void increaseItemCount(@PathVariable("id") Integer id){
        cartService.increaseItemCount(id);
    }

    //4. Giảm số lượng sản phẩm trong giỏ hàng (PUT : /api/v1/cartItems/{id}/decrement)
    @PutMapping("/{id}/decrement")
    public void decreaseItemCount(@PathVariable("id") Integer id){
        cartService.decreaseItemCount(id);
    }
}
