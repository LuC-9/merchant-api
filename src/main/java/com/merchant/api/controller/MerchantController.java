package com.merchant.api.controller;

import com.merchant.api.model.Merchant;
import com.merchant.api.service.MerchantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/merchants")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Merchant", description = "Merchant management APIs")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping
    @Operation(
        summary = "Get all merchants",
        description = "Retrieve a list of all merchants"
    )
    public ResponseEntity<List<Merchant>> getAllMerchants() {
        return ResponseEntity.ok(merchantService.getAllMerchants());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get merchant by ID",
        description = "Retrieve a merchant by their ID"
    )
    public ResponseEntity<Merchant> getMerchantById(@PathVariable Long id) {
        return ResponseEntity.ok(merchantService.getMerchantById(id));
    }

    @PostMapping
    @Operation(
        summary = "Create new merchant",
        description = "Create a new merchant with the provided details"
    )
    public ResponseEntity<Merchant> createMerchant(@Valid @RequestBody Merchant merchant) {
        Merchant createdMerchant = merchantService.createMerchant(merchant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMerchant);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update merchant",
        description = "Update an existing merchant's details"
    )
    public ResponseEntity<Merchant> updateMerchant(
            @PathVariable Long id,
            @Valid @RequestBody Merchant merchantDetails) {
        return ResponseEntity.ok(merchantService.updateMerchant(id, merchantDetails));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete merchant",
        description = "Delete a merchant by their ID"
    )
    public ResponseEntity<Void> deleteMerchant(@PathVariable Long id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.noContent().build();
    }
} 