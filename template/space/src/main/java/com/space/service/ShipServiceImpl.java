package com.space.service;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {

//    @PersistenceContext
//    private EntityManager entityManager;

//    @Autowired
//    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private ShipRepository shipRepository;

    @Override
    @Transactional
    public void save(Ship ship) {
        shipRepository.save(ship);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        shipRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Ship getById(Long id) {
        return shipRepository.getOne(id);
    }

    @Override
    @Transactional
    public List<Ship> getAll() {
        return shipRepository.findAll();
    }

    @Override
    @Transactional
    public List<Ship> query(Ship ship) {
        return shipRepository.findAll();
    }
}
