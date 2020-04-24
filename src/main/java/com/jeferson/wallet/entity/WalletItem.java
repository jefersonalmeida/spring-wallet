package com.jeferson.wallet.entity;

import com.jeferson.wallet.util.enums.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "wallets_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletItem implements Serializable {

    private static final long serialVersionUID = -9065556819769322761L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "wallet", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallet;

    @Column(nullable = false)
    @NotNull()
    private Date date;

    @Column(nullable = false)
    @NotNull()
    @Enumerated(EnumType.STRING)
    private TypeEnum type;

    @Column(nullable = false)
    @NotNull()
    private String description;

    @Column(nullable = false)
    @NotNull()
    private BigDecimal value;
}
