package com.space.service;

import com.space.model.Ship;

import java.util.List;

public interface ShipService {
    void create(Ship ship);
    void update(Ship ship);
    void deleteById(Long id);
    Ship getById(Long id);
    List<Ship> getAll();
}
