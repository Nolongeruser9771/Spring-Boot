package com.example.weddingofhuongsan.service;

import com.example.weddingofhuongsan.entity.Menu;
import com.example.weddingofhuongsan.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    MenuRepository menuRepository;

    //Chi phi 1 cỗ
    @Override
    public Double totalAmountPerTable() {
        double totalAmount = 0d;
        List<Menu> menus = menuRepository.findAll();
        for (Menu menu: menus){
            totalAmount+=menu.getPrice();
        }
        return totalAmount;
    }

    @Override
    public Menu addMenu(Menu menu) {
        return menuRepository.save(menu);
    }
}
