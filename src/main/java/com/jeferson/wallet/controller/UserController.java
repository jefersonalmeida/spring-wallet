package com.jeferson.wallet.controller;

import com.jeferson.wallet.dto.UserDTO;
import com.jeferson.wallet.entity.User;
import com.jeferson.wallet.response.Response;
import com.jeferson.wallet.service.UserService;
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
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<Response<UserDTO>> store(@Valid @RequestBody UserDTO dto, BindingResult result) {

        Response<UserDTO> response = new Response<>();
        if(result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        User user = service.save(convertDTOToEntity(dto));

        response.setData(convertEntityToDTO(user));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private User convertDTOToEntity(UserDTO dto) {
        User u = new User();
        u.setId(dto.getId());
        u.setName(dto.getName());
        u.setEmail(dto.getEmail());
        u.setPassword(dto.getPassword());
        return u;
    }

    private UserDTO convertEntityToDTO(User u) {
        UserDTO dto = new UserDTO();
        dto.setId(u.getId());
        dto.setName(u.getName());
        dto.setEmail(u.getEmail());
        dto.setPassword(u.getPassword());
        return dto;
    }
}
