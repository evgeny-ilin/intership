package com.space.service;

import com.space.model.Ship;

import java.util.List;

public interface ShipService {
    Ship create(Ship ship);
    Ship update(Ship ship);
    void deleteById(Long id);
    Ship getById(Long id);
    List<Ship> getAll(Integer pageNumber, Integer pageSize);
    Integer getCount();
}
