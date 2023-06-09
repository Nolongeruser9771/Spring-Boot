package com.example.blogbackend.repository;

import com.example.blogbackend.dto.projection.CategoryPublic;
import com.example.blogbackend.dto.projection.CategoryWebPublic;
import com.example.blogbackend.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<CategoryPublic> findByOrderByNameDesc(Pageable pageable);

    Category findByName(String name);

    boolean existsByName(String name);

    List<Category> findByIdIn(Collection<Integer> ids);


}