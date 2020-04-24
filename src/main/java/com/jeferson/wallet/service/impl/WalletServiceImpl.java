package com.jeferson.wallet.service.impl;

import com.jeferson.wallet.entity.Wallet;
import com.jeferson.wallet.repository.WalletRepository;
import com.jeferson.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository repository;

    @Override
    public Wallet save(Wallet u) {
        return repository.save(u);
    }
}
