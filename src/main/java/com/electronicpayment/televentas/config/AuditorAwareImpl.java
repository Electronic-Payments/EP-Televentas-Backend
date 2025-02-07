package com.electronicpayment.televentas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.electronicpayment.televentas.shared.entities.User;
import com.electronicpayment.televentas.shared.repositories.IUserRepository;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<User> {

    @Autowired
    private IUserRepository userRepository;


    @Override
    public Optional<User> getCurrentAuditor() { 
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return Optional.empty();
        }
        
        String username = authentication.getName();

        User user = this.userRepository.findByDocument(username); 

        return Optional.of(user);
    }
}