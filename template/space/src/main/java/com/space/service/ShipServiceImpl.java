package com.space.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
TODO Нужно ли делать проверку?
 6. Не валидным считается id, если он:
 - не числовой
 - не целое число
 - не положительный
 */

@Service
@Transactional
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository repository;

    @Override
    public Ship create(Ship ship) {
        if (ship.isUsed() == null) ship.setUsed(false);
        return repository.save(ship);
    }

    @Override
    public Ship update(Ship ship) {
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
    public List<Ship> getAll(Integer pageNumber, Integer pageSize) {
        return (List<Ship>) repository.findAll();
    }

    @Override
    public Integer getCount() {
        return Math.toIntExact(repository.count());
    }
}
