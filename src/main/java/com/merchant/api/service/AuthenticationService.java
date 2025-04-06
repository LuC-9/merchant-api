package com.merchant.api.service;

import com.merchant.api.model.Merchant;
import com.merchant.api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final MerchantService merchantService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String login(String email, String password) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );
        
        Merchant merchant = merchantService.findByEmail(email);
        UserDetails userDetails = new User(merchant.getEmail(), merchant.getPassword(), new ArrayList<>());
        return jwtService.generateToken(userDetails);
    }

    public String register(Merchant merchant) {
        Merchant savedMerchant = merchantService.createMerchant(merchant);
        UserDetails userDetails = new User(savedMerchant.getEmail(), savedMerchant.getPassword(), new ArrayList<>());
        return jwtService.generateToken(userDetails);
    }
} 