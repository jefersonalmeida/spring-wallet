package com.jeferson.wallet.repository;

import com.jeferson.wallet.entity.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserWalletRepository extends JpaRepository<UserWallet, Long> {

    Optional<UserWallet> findByUsersIdAndWalletsId(Long user, Long wallet);
}
