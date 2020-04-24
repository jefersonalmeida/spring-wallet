package com.jeferson.wallet.service.impl;

import com.jeferson.wallet.entity.User;
import com.jeferson.wallet.repository.UserRepository;
import com.jeferson.wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public User save(User u) {
        return repository.save(u);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmailEquals(email);
    }
}
