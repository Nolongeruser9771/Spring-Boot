package com.example.weddingofhuongsan.service;

import com.example.weddingofhuongsan.entity.Gift;

public interface GiftService {
    Double totalAmountOfGift();

    Gift addGift(Gift gift);
}
