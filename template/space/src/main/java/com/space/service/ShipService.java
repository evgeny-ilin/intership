package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.Ship;

import java.util.List;
import java.util.Optional;

public interface ShipService {
    Ship create(Ship ship);
    Ship update(Ship ship);
    void deleteById(Long id);
    Ship getById(Long id);
    List<Ship> getAll(Optional<Integer> pageNumber, Optional<Integer> pageSize, Optional<ShipOrder  > shipOrder);
    Integer getCount();
}
