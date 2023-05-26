package com.example.ewallet;

import com.example.ewallet.entity.Account;
import com.example.ewallet.entity.Wallet;
import com.example.ewallet.repository.AccountRepository;
import com.example.ewallet.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
@AllArgsConstructor
public class EWalletApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EWalletApplication.class, args);
    }

    private AccountRepository accountRepository;
    private WalletRepository walletRepository;

    @Override
    public void run(String... args) throws Exception {
        Wallet wallet = new Wallet();
        wallet.setBalance(2000000);
        walletRepository.save(wallet);

        Account account = new Account();
        account.setUsername("nguyen");
        account.setPassword("123");
        account.setWalletId(wallet);
        accountRepository.save(account);

        Optional<Account> rs = accountRepository.findById(1);
        System.out.println(rs.get().toString());
    }
}
