package com.jeferson.wallet.controller;

import com.jeferson.wallet.dto.UserDTO;
import com.jeferson.wallet.entity.User;
import com.jeferson.wallet.response.Response;
import com.jeferson.wallet.service.UserService;
import com.jeferson.wallet.util.BCrypt;
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
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<Response<UserDTO>> store(@Valid @RequestBody UserDTO dto, BindingResult result) {

        Response<UserDTO> response = new Response<>();
        if(result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        User user = service.save(this.convertDTOToEntity(dto));

        response.setData(this.convertEntityToDTO(user));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private User convertDTOToEntity(UserDTO dto) {
        User object = new User();
        object.setId(dto.getId());
        object.setName(dto.getName());
        object.setEmail(dto.getEmail());
        object.setPassword(BCrypt.getHash(dto.getPassword()));
        return object;
    }

    private UserDTO convertEntityToDTO(User object) {
        UserDTO dto = new UserDTO();
        dto.setId(object.getId());
        dto.setName(object.getName());
        dto.setEmail(object.getEmail());
        return dto;
    }
}
