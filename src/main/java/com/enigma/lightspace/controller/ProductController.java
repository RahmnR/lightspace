package com.enigma.lightspace.controller;

import com.enigma.lightspace.model.request.ProductRequest;
import com.enigma.lightspace.model.response.CommonResponse;
import com.enigma.lightspace.model.response.ProductResponse;
import com.enigma.lightspace.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/products")
public class ProductController {
    private final ProductService productService;

        @PostMapping
        @PreAuthorize("hasRole('VENDOR')")
        public ResponseEntity<?> createNewProduct(@RequestBody ProductRequest request,Authentication authentication) {
            ProductResponse productResponse = productService.create(request, authentication);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(CommonResponse.<ProductResponse>builder()
                            .statusCode(HttpStatus.CREATED.value())
                            .message("Successfully create new product")
                            .data(productResponse)
                            .build());
        }

    @PreAuthorize("hasRole('VENDOR')")
    @PostMapping(path = "/bulk")
    public ResponseEntity<?> createBulkProduct(@RequestBody List<ProductRequest> products,Authentication authentication) {
        List<ProductResponse> productResponses = productService.createBulk(products,authentication);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<List<ProductResponse>>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully create bulk customer")
                        .data(productResponses)
                        .build());
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<?> getProductById(Authentication authentication, @PathVariable String code) {
        ProductResponse productResponse = productService.getByCode(code);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get customer")
                        .data(productResponse)
                        .build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllProduct() {
        List<ProductResponse> productResponses = productService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all customer")
                        .data(productResponses)
                        .build());
    }

    @PreAuthorize("hasRole('VENDOR') and @userSecurity.checkVendor(authentication,#vendor.getById())")
    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest request) {
        ProductResponse productResponse = productService.update(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<ProductResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully update customer")
                        .data(productResponse)
                        .build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{code}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "code") String code) {
        productService.deleteByCode(code);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<String>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully delete customer")
                        .build());
    }

}