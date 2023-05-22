package com.example.weddingofhuongsan.service;

import com.example.weddingofhuongsan.entity.Gift;
import com.example.weddingofhuongsan.repository.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftServiceImpl implements GiftService {

    @Autowired
    GiftRepository giftRepository;

    //Tong tien mung
    @Override
    public Double totalAmountOfGift() {
        List<Gift> gifts = giftRepository.findAll();
        double totalAmount = 0;
        for (Gift gift:gifts){
            totalAmount+=gift.getPrice();
        }
        return totalAmount;
    }

    @Override
    public Gift addGift(Gift gift) {
        return giftRepository.save(gift);
    }
}
