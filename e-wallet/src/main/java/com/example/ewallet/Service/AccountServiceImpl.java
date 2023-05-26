package com.example.ewallet.Service;

import com.example.ewallet.entity.Account;
import com.example.ewallet.entity.Wallet;
import com.example.ewallet.exception.NotFoundException;
import com.example.ewallet.repository.AccountRepository;
import com.example.ewallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private WalletRepository walletRepository;

    public List<Account> findAllAccount(){
        return accountRepository.findAll();
    }

    public Account findById(Integer id) {
        Optional<Account> account =  accountRepository.findById(id);
        if (!account.isPresent()) {
            throw new NotFoundException("Not found id "+id);
        }
        return account.get();
    }

    public Account updateBalanceByAccountId(Integer id, Double balance){
        Optional<Account> account =  accountRepository.findById(id);
        if (!account.isPresent()) {
            throw new NotFoundException("Not found id "+id);
        }
        Optional<Wallet> wallet = walletRepository.findById(id);
        wallet.get().setBalance(balance);
        walletRepository.save(wallet.get());
        return account.get();
    }
}
