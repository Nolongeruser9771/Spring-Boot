package com.example.ewallet.Service;

import com.example.ewallet.entity.Wallet;
import com.example.ewallet.exception.NotFoundException;
import com.example.ewallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService{
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<Wallet> findWalletByBalance(Double balance) {
        List<Wallet> wallet = walletRepository.findByBalance(balance);
        if (wallet.size()==0) {
            throw new NotFoundException("Not found Balance = "+ balance);
        }
        return wallet;
    }
}
