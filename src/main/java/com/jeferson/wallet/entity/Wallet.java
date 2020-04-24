package com.jeferson.wallet.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "wallets")
@Data
public class Wallet implements Serializable {

    private static final long serialVersionUID = -7576081055138532839L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal value;
}
