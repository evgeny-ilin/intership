package com.space.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository repository;

    @Override
    public Ship save(Ship ship) {
        return repository.save(ship);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Ship getById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Ship> getAll() {
        return (List<Ship>) repository.findAll();
    }

}
