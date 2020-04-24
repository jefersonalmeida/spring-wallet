package com.jeferson.wallet.services;

import com.jeferson.wallet.entities.User;

import java.util.Optional;

public interface UserService {

    User save(User u);

    Optional<User> findByEmail(String email);
}
