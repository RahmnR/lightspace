package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.Warehouse;
import com.enigma.lightspace.repository.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WarehouseServiceImplTest {

    @Mock
    private WarehouseRepository warehouseRepository;

    private WarehouseServiceImpl warehouseService;

    @BeforeEach
    public void setUp() {
        warehouseService = new WarehouseServiceImpl(warehouseRepository);
    }

    @Test
    public void testUpdateWarehouse() {

    }

    @Test
    public void testGetAllWarehouses() {
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setCode("W001");
        warehouse1.setProduct("Product A");
        warehouse1.setStock(10);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setCode("W002");
        warehouse2.setProduct("Product B");
        warehouse2.setStock(20);

        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse1);
        warehouses.add(warehouse2);

        when(warehouseRepository.findAll()).thenReturn(warehouses);

        List<Warehouse> result = warehouseService.getAll();

        assertEquals(2, result.size());
        assertEquals(10, result.get(0).getStock());
        assertEquals(20, result.get(1).getStock());
    }

    @Test
    void getByDate() {
    }
}