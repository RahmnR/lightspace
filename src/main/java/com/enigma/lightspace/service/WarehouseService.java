package com.enigma.lightspace.service;


import com.enigma.lightspace.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    Warehouse update(Warehouse warehouse);
    List<Warehouse> getAll();
    Warehouse getByDate(Warehouse warehouse);
}
