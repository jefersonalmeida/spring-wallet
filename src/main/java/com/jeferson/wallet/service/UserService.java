package com.jeferson.wallet.service;

import com.jeferson.wallet.entity.User;

import java.util.Optional;

public interface UserService {

    User save(User object);

    Optional<User> findByEmail(String email);
}
