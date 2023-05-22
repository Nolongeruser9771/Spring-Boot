package com.example.weddingofhuongsan.service;

import com.example.weddingofhuongsan.entity.Menu;

public interface MenuService {
    Double totalAmountPerTable();

    Menu addMenu(Menu menu);
}
