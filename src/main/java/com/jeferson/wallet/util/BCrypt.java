package com.jeferson.wallet.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCrypt {

    public static String getHash(String password) {
        if (password.isEmpty()) {
            return null;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
