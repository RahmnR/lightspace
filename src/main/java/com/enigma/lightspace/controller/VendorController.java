package com.enigma.lightspace.controller;

import com.enigma.lightspace.model.request.OrderRequest;
import com.enigma.lightspace.model.request.VendorRequest;
import com.enigma.lightspace.model.response.CommonResponse;
import com.enigma.lightspace.model.response.VendorResponse;
import com.enigma.lightspace.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/vendors")
public class VendorController {
    private final VendorService vendorService;

    @PreAuthorize("hasRole('VENDOR')")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody VendorRequest request, Authentication authentication) {
        VendorResponse update = vendorService.update(request,authentication);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get transaction by id")
                        .data(update)
                        .build());
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll() {
        List<VendorResponse> responses = vendorService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get transaction by id")
                        .data(responses)
                        .build());
    }
}
