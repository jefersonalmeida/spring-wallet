package com.jeferson.wallet.service;

import com.jeferson.wallet.entity.WalletItem;
import com.jeferson.wallet.util.enums.TypeEnum;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WalletItemService {

    Optional<WalletItem> findById(Long id);

    void deleteById(Long id);

    WalletItem save(WalletItem object);

    Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page);

    List<WalletItem> findByWalletAndType(Long wallet, TypeEnum type);

    BigDecimal sumByWalletId(Long wallet);

}
