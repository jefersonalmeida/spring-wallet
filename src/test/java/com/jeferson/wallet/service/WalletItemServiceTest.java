package com.jeferson.wallet.service;

import com.jeferson.wallet.entity.Wallet;
import com.jeferson.wallet.entity.WalletItem;
import com.jeferson.wallet.repository.WalletItemRepository;
import com.jeferson.wallet.util.enums.TypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
public class WalletItemServiceTest {

    private static final Date DATE = new Date();
    private static final TypeEnum TYPE = TypeEnum.EN;
    private static final String DESCRIPTION = "Conta de Luz";
    private static final BigDecimal VALUE = BigDecimal.valueOf(65);
    @MockBean
    WalletItemRepository repository;
    @Autowired
    WalletItemService service;

    @Test
    public void testSave() {
        BDDMockito.given(repository.save(Mockito.any(WalletItem.class))).willReturn(getMockiWalletItem());

        WalletItem response = service.save(new WalletItem());

        assertNotNull(response);
        assertEquals(response.getDescription(), DESCRIPTION);
        assertEquals(response.getValue().compareTo(VALUE), 0);
    }

    @Test
    public void testFindBetweenDates() {
        List<WalletItem> list = new ArrayList<>();
        list.add(getMockiWalletItem());
        Page<WalletItem> page = new PageImpl<>(list);

        BDDMockito.given(repository
                .findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(
                        Mockito.anyLong(),
                        Mockito.any(Date.class),
                        Mockito.any(Date.class),
                        Mockito.any(PageRequest.class)
                ))
                .willReturn(page);

        Page<WalletItem> response = service.findBetweenDates(1L, new Date(), new Date(), 0);

        assertNotNull(response);
        assertEquals(response.getContent().size(), 1);
        assertEquals(response.getContent().get(0).getDescription(), DESCRIPTION);
    }

    @Test
    public void testFindByType() {
        List<WalletItem> list = new ArrayList<>();
        list.add(getMockiWalletItem());

        BDDMockito.given(repository.findByWalletIdAndType(Mockito.anyLong(), Mockito.any(TypeEnum.class))).willReturn(list);

        List<WalletItem> response = service.findByWalletAndType(1L, TypeEnum.EN);

        assertNotNull(response);
        assertEquals(response.get(0).getType(), TYPE);
    }

    @Test
    public void testSumByWallet() {
        BigDecimal value = BigDecimal.valueOf(45);

        BDDMockito.given(repository.sumByWalletId(Mockito.anyLong())).willReturn(value);

        BigDecimal response = service.sumByWalletId(1L);

        assertEquals(response.compareTo(value), 0);
    }

    private WalletItem getMockiWalletItem() {
        Wallet w = new Wallet();
        w.setId(1L);

        return new WalletItem(null, w, DATE, TYPE, DESCRIPTION, VALUE);
    }
}
