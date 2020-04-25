package com.jeferson.wallet.service.impl;

import com.jeferson.wallet.entity.UserWallet;
import com.jeferson.wallet.repository.UserWalletRepository;
import com.jeferson.wallet.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserWalletServiceImpl implements UserWalletService {

    @Autowired
    UserWalletRepository repository;

    @Override
    public UserWallet save(UserWallet object) {
        return repository.save(object);
    }

    @Override
    public Optional<UserWallet> findByUsersIdAndWalletsId(Long user, Long wallet) {
        return repository.findByUsersIdAndWalletsId(user, wallet);
    }
}
