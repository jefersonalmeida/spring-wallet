package com.jeferson.wallet.controller;

import com.jeferson.wallet.dto.UserWalletDTO;
import com.jeferson.wallet.entity.User;
import com.jeferson.wallet.entity.UserWallet;
import com.jeferson.wallet.entity.Wallet;
import com.jeferson.wallet.response.Response;
import com.jeferson.wallet.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "user-wallet")
public class UserWalletController {

    @Autowired
    private UserWalletService service;

    @PostMapping
    public ResponseEntity<Response<UserWalletDTO>> store(@Valid @RequestBody UserWalletDTO dto, BindingResult result) {

        Response<UserWalletDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        UserWallet object = service.save(this.convertDTOToEntity(dto));

        response.setData(this.convertEntityToDTO(object));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private UserWallet convertDTOToEntity(UserWalletDTO dto) {
        UserWallet object = new UserWallet();

        User u = new User();
        u.setId(dto.getUsers());

        Wallet w = new Wallet();
        w.setId(dto.getWallets());

        object.setId(dto.getId());
        object.setUsers(u);
        object.setWallets(w);
        return object;
    }

    private UserWalletDTO convertEntityToDTO(UserWallet object) {
        UserWalletDTO dto = new UserWalletDTO();
        dto.setId(object.getId());
        dto.setUsers(object.getUsers().getId());
        dto.setWallets(object.getWallets().getId());
        return dto;
    }
}
