package com.jeferson.wallet.controller;

import com.jeferson.wallet.dto.WalletDTO;
import com.jeferson.wallet.entity.Wallet;
import com.jeferson.wallet.response.Response;
import com.jeferson.wallet.service.WalletService;
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
@RequestMapping("wallet")
public class WalletController {

    @Autowired
    private WalletService service;

    @PostMapping
    public ResponseEntity<Response<WalletDTO>> store(@Valid @RequestBody WalletDTO dto, BindingResult result) {

        Response<WalletDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        Wallet object = service.save(this.convertDTOToEntity(dto));

        response.setData(this.convertEntityToDTO(object));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private Wallet convertDTOToEntity(WalletDTO dto) {
        Wallet object = new Wallet();
        object.setId(dto.getId());
        object.setName(dto.getName());
        object.setValue(dto.getValue());
        return object;
    }

    private WalletDTO convertEntityToDTO(Wallet object) {
        WalletDTO dto = new WalletDTO();
        dto.setId(object.getId());
        dto.setName(object.getName());
        dto.setValue(object.getValue());
        return dto;
    }
}
