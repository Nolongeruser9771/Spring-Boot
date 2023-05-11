package com.example.shoppingcart.service;

public interface FileService {
    String getThumnailById(Integer id);

    byte[] readFile(String fileName);
}
