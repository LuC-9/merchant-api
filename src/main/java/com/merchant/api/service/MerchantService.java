package com.merchant.api.service;

import com.merchant.api.model.Merchant;
import com.merchant.api.repository.MerchantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    public Merchant getMerchantById(Long id) {
        return merchantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Merchant not found with id: " + id));
    }

    public Merchant createMerchant(Merchant merchant) {
        if (merchantRepository.existsByEmail(merchant.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        
        // Encode password before saving
        merchant.setPassword(passwordEncoder.encode(merchant.getPassword()));
        return merchantRepository.save(merchant);
    }

    public Merchant updateMerchant(Long id, Merchant merchantDetails) {
        Merchant merchant = getMerchantById(id);
        
        merchant.setBusinessName(merchantDetails.getBusinessName());
        merchant.setPhoneNumber(merchantDetails.getPhoneNumber());
        merchant.setAddress(merchantDetails.getAddress());
        merchant.setBusinessType(merchantDetails.getBusinessType());
        merchant.setRegistrationNumber(merchantDetails.getRegistrationNumber());
        merchant.setTaxId(merchantDetails.getTaxId());
        merchant.setActive(merchantDetails.isActive());
        
        return merchantRepository.save(merchant);
    }

    public void deleteMerchant(Long id) {
        if (!merchantRepository.existsById(id)) {
            throw new EntityNotFoundException("Merchant not found with id: " + id);
        }
        merchantRepository.deleteById(id);
    }

    public Merchant findByEmail(String email) {
        return merchantRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Merchant not found with email: " + email));
    }
} 