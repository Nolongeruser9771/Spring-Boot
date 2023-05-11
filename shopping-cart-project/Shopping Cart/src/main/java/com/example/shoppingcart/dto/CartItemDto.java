package com.example.shoppingcart.dto;

import com.example.shoppingcart.entity.Course;

public record CartItemDto(Integer id,
                          Course course,
                          Integer count) {
}
