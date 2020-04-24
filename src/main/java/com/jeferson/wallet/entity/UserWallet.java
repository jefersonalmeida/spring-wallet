package com.jeferson.wallet.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_wallets")
@Data
public class UserWallet implements Serializable {


    private static final long serialVersionUID = -8329395105446352469L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "users", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User users;

    @JoinColumn(name = "wallets", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallets;
}
