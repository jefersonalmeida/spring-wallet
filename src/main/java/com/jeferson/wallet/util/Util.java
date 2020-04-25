package com.jeferson.wallet.util;

import com.jeferson.wallet.entity.User;
import com.jeferson.wallet.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Util {

    private static UserService staticService;

    public Util(UserService service) {
        Util.staticService = service;
    }

    public static Long getAuthenticatedUserId() {
        try {
            Optional<User> user = staticService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            return user.map(User::getId).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}