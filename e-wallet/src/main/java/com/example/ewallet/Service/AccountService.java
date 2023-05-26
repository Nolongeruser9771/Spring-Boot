package com.example.ewallet.Service;

import com.example.ewallet.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAllAccount();
    Account findById(Integer id);
    Account updateBalanceByAccountId(Integer id, Double balance);
}
