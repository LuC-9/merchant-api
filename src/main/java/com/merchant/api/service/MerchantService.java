package com.merchant.api.service;

import com.merchant.api.model.Merchant;
import com.merchant.api.repository.MerchantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

@Service
@Transactional
public class MerchantService {

    private final MerchantRepository merchantRepository;
    private final PasswordEncoder passwordEncoder;

    public MerchantService(MerchantRepository merchantRepository, PasswordEncoder passwordEncoder) {
        this.merchantRepository = merchantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Cacheable(value = "merchants")
    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    @Cacheable(value = "merchant", key = "#id")
    public Merchant getMerchantById(Long id) {
        return merchantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Merchant not found with id: " + id));
    }

    @CachePut(value = "merchant", key = "#result.id")
    @CacheEvict(value = "merchants", allEntries = true)
    public Merchant createMerchant(Merchant merchant) {
        if (merchantRepository.existsByEmail(merchant.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        
        // Encode password before saving
        merchant.setPassword(passwordEncoder.encode(merchant.getPassword()));
        return merchantRepository.save(merchant);
    }

    @CachePut(value = "merchant", key = "#id")
    @CacheEvict(value = "merchants", allEntries = true)
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

    @CacheEvict(value = {"merchant", "merchants"}, allEntries = true)
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
