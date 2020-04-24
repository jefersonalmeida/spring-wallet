package com.jeferson.wallet.repository;

import com.jeferson.wallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailEquals(String email);
}
