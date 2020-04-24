package com.jeferson.wallet.repository;

import com.jeferson.wallet.entity.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWalletRepository extends JpaRepository<UserWallet, Long> {

}
