package com.example.shoppingcart.mapper;

import com.example.shoppingcart.dto.CartItemDto;
import com.example.shoppingcart.exception.ResourceNotFoundException;
import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.entity.Course;
import com.example.shoppingcart.repository.CourseRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CartMapper {
    private static CourseRepository courseRepository;
    public CartMapper(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public static CartItemDto cartItemDto(CartItem cartItem){
        int courseId = cartItem.getCourseId();
        Optional<Course> courseOptional = courseRepository.getCourseById(courseId);
        if(courseOptional.isPresent()){
            Course course = courseOptional.get();
            return new CartItemDto(cartItem.getId(), course, cartItem.getCount());
        }
        throw new ResourceNotFoundException("không có course với id = " + courseId);
    }
}
