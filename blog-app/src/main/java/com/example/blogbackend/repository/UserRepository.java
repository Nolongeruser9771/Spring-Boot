package com.example.blogbackend.repository;

import com.example.blogbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRoles_NameIn(List<String> roles);
}