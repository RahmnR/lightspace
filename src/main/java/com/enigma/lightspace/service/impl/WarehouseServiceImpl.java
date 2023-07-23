package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.Category;
import com.enigma.lightspace.entity.Warehouse;
import com.enigma.lightspace.repository.WarehouseRepository;
import com.enigma.lightspace.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;
    @Transactional(rollbackOn = Exception.class)
    @Override
    public Warehouse update(Warehouse warehouse) {
        Warehouse storage = getOrUpdate(warehouse.getCode());
        storage.setProduct(warehouse.getProduct());
        storage.setStock(storage.getStock()+ warehouse.getStock());
        return warehouseRepository.saveAndFlush(storage);
    }

    @Override
    public List<Warehouse> getAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse getByDate(Warehouse warehouse) {
        return null;
    }

    private Warehouse getOrUpdate(String code){
        return warehouseRepository.findWarehouseByCode(code).orElseGet(() ->
                warehouseRepository.saveAndFlush(Warehouse.builder()
                        .code(code).stock(0).build()));
    }

}
