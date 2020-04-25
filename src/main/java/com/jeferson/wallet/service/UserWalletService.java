package com.jeferson.wallet.service;

import com.jeferson.wallet.entity.UserWallet;

import java.util.Optional;

public interface UserWalletService {

    UserWallet save(UserWallet object);

    Optional<UserWallet> findByUsersIdAndWalletsId(Long user, Long wallet);
}
