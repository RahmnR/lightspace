package com.enigma.lightspace.controller;

import com.enigma.lightspace.entity.Warehouse;
import com.enigma.lightspace.model.response.CommonResponse;
import com.enigma.lightspace.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/warehouses")
public class WarehouseController {
    private final WarehouseService warehouseService;
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Warehouse> warehouses = warehouseService.getAll();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<List<Warehouse>>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Get All")
                        .data(warehouses)
                        .build());
    }

}
