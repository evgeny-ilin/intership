package com.space.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private EntityManager entityManager;

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
    public List<Ship> getAll(Optional<Integer> pageNumber, Optional<Integer> pageSize, Optional<ShipOrder> shipOrder) {
        Integer pSize = pageSize.get();
        Integer pNumber = pageNumber.get();
        String hqlOrderBy = shipOrder.map(order -> " order by " + order.getFieldName()).orElse("");
        String hql = "from Ship" + hqlOrderBy;

        Query<Ship> query = (Query<Ship>) entityManager.createQuery(hql);
        query.setMaxResults(pSize);
        query.setFirstResult(pNumber * pSize);

        List<Ship> ships = query.getResultList();

        return ships;
    }

    @Override
    public Integer getCount() {
        return Math.toIntExact(repository.count());
    }
}
