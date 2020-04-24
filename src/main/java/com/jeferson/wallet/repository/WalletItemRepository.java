package com.jeferson.wallet.repository;

import com.jeferson.wallet.entity.WalletItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletItemRepository extends JpaRepository<WalletItem, Long> {

}
