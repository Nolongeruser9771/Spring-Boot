package com.example.ewallet.controller;

import com.example.ewallet.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    //Viết api hiển thị tất cả account và thông tin về wallet của account đó
    @GetMapping("info")
    public ResponseEntity<?> getAccountAndWalletInfo(){
        return ResponseEntity.ok().body(accountService.findAllAccount());
    }

    //Viết api hiển thị account theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(accountService.findById(id));
    }

    //Viết api Thực hiện update số dư theo account id
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateWalletByAccountId(@PathVariable Integer id,
                                                     @RequestParam double balance){
        return ResponseEntity.ok().body(accountService.updateBalanceByAccountId(id, balance));
    }
}
