package com.example.weddingofhuongsan.repository;

import com.example.weddingofhuongsan.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Integer> {
}
