package com.enigma.lightspace.security;

import com.enigma.lightspace.entity.Vendor;
import com.enigma.lightspace.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSecurity {
    private final VendorService vendorService;

//    public boolean checkSeller(Authentication authentication, String storeId) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String email = sellerService.getEmailByStoreId(storeId);
//        return userDetails.getUsername().equals(email);
//    }

    public boolean checkVendor(Authentication authentication, String vendorId) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Vendor vendor = vendorService.getById(vendorId);
        return vendor.getUserCredential().getEmail().equals(userDetails.getUsername());
    }
}
