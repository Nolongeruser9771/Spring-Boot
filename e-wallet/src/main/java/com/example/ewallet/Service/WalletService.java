package com.example.ewallet.Service;

import com.example.ewallet.entity.Wallet;

import java.util.List;

public interface WalletService {
    List<Wallet> findWalletbyBalance(Double findByWallet);
}
