package com.merchant.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "merchants")
public class Merchant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    
    @NotBlank(message = "Business name is required")
    private String businessName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;
    
    @NotBlank(message = "Password is required")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    
    private String address;
    
    @Column(name = "business_type")
    private String businessType;
    
    @Column(name = "registration_number")
    private String registrationNumber;
    
    @Column(name = "tax_id")
    private String taxId;
    
    private boolean active = true;
    
    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;

    // Getters
    public Long getId() {
        return id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getBusinessType() {
        return businessType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getTaxId() {
        return taxId;
    }

    public boolean isActive() {
        return active;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Constructors
    public Merchant() {
    }

    public Merchant(Long id, String businessName, String email, String password, String phoneNumber,
                   String address, String businessType, String registrationNumber, String taxId,
                   boolean active, java.time.LocalDateTime createdAt, java.time.LocalDateTime updatedAt) {
        this.id = id;
        this.businessName = businessName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.businessType = businessType;
        this.registrationNumber = registrationNumber;
        this.taxId = taxId;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
        updatedAt = createdAt;
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = java.time.LocalDateTime.now();
    }

    // Builder pattern implementation
    public static MerchantBuilder builder() {
        return new MerchantBuilder();
    }

    public static class MerchantBuilder {
        private final Merchant merchant = new Merchant();

        public MerchantBuilder id(Long id) {
            merchant.setId(id);
            return this;
        }

        public MerchantBuilder businessName(String businessName) {
            merchant.setBusinessName(businessName);
            return this;
        }

        public MerchantBuilder email(String email) {
            merchant.setEmail(email);
            return this;
        }

        public MerchantBuilder password(String password) {
            merchant.setPassword(password);
            return this;
        }

        public MerchantBuilder phoneNumber(String phoneNumber) {
            merchant.setPhoneNumber(phoneNumber);
            return this;
        }

        public MerchantBuilder address(String address) {
            merchant.setAddress(address);
            return this;
        }

        public MerchantBuilder businessType(String businessType) {
            merchant.setBusinessType(businessType);
            return this;
        }

        public MerchantBuilder registrationNumber(String registrationNumber) {
            merchant.setRegistrationNumber(registrationNumber);
            return this;
        }

        public MerchantBuilder taxId(String taxId) {
            merchant.setTaxId(taxId);
            return this;
        }

        public MerchantBuilder active(boolean active) {
            merchant.setActive(active);
            return this;
        }

        public Merchant build() {
            return merchant;
        }
    }
} 