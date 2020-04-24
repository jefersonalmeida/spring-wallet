package com.jeferson.wallet.repository;

import com.jeferson.wallet.entity.Wallet;
import com.jeferson.wallet.entity.WalletItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
public class WalletItemRepositoryTest {

    private static final Date DATE = new Date();
    private static final String TYPE = "EN";
    private static final String DESCRIPTION = "Conta de Luz";
    private static final BigDecimal VALUE = BigDecimal.valueOf(65);

    @Autowired
    WalletItemRepository walletItemRepository;

    @Autowired
    WalletRepository walletRepository;

    @Test
    public void testSave() {

        Wallet wallet = new Wallet();
        wallet.setName("Carteira 1");
        wallet.setValue(BigDecimal.valueOf(500));
        walletRepository.save(wallet);

        WalletItem walletItem = new WalletItem(1L, wallet, DATE, TYPE, DESCRIPTION, VALUE);

        WalletItem response = walletItemRepository.save(walletItem);

        assertNotNull(response);
        assertEquals(response.getWallet().getId(), wallet.getId());
        assertEquals(response.getDate(), DATE);
        assertEquals(response.getType(), TYPE);
        assertEquals(response.getDescription(), DESCRIPTION);
        assertEquals(response.getValue(), VALUE);
    }
}