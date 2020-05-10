package com.space.service;

import com.space.model.Ship;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void create(Ship ship) {

    }

    @Override
    public void update(Ship ship) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Ship getById(Long id) {
        return null;
    }

    @Override
    public List<Ship> getAll() {
        return null;
    }
}
