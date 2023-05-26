package com.example.ewallet.controller;

import com.example.ewallet.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    //Viết api hiển thị wallet theo số dư
    @GetMapping("/filter-by-balance")
    public ResponseEntity<?> findWalletByBalance(@RequestParam double balance){
        return ResponseEntity.ok().body(walletService.findWalletByBalance(balance));
    }
}
