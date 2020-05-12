package com.space.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.sql.Date;

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
        ship.setRating();
        return repository.save(ship);
    }

    @Override
    public Ship update(Ship ship) {
        //Check if updatable ship exists
        getById(ship.getId());
        ship.setRating();
        return repository.save(ship);
    }

    @Override
    public void deleteById(Long id) {
        Ship.checkId(id);
        getById(id);
        repository.deleteById(id);
    }

    @Override
    public Ship getById(Long id) {
        Ship ship = null;
        try {
            ship = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Ship does not exist in database by id:"+id);
        }

        return ship;
    }

    @Override
    public String getHQL(Optional<String> name,
                             Optional<String> planet,
                             Optional<ShipType> shipType,
                             Optional<Long> after,
                             Optional<Long> before,
                             Optional<Boolean> isUsed,
                             Optional<Double> minSpeed,
                             Optional<Double> maxSpeed,
                             Optional<Integer> minCrewSize,
                             Optional<Integer> maxCrewSize,
                             Optional<Double> minRating,
                             Optional<Double> maxRating,
                             Optional<ShipOrder> order
                             )
    {
        String hqlFrom = "from Ship";

        String hqlWhereClause = "";
        hqlWhereClause += name.map(s -> "name like '%" + s + "%' and ").orElse("");
        hqlWhereClause += planet.map(s -> "planet like '%" + s + "%' and ").orElse("");
        hqlWhereClause += shipType.map(s -> "shipType = '" + s.name() + "' and ").orElse("");
        hqlWhereClause += after.map(s -> "prodDate >= '" + new Date(s) + "' and ").orElse("");
        hqlWhereClause += before.map(s -> "prodDate <= '" + new Date(s) + "' and ").orElse("");
        hqlWhereClause += isUsed.map(s -> "isUsed = '" + s + "' and ").orElse("");
        hqlWhereClause += minSpeed.map(s -> "speed >= '" + s + "' and ").orElse("");
        hqlWhereClause += maxSpeed.map(s -> "speed <= '" + s + "' and ").orElse("");
        hqlWhereClause += minCrewSize.map(s -> "crewSize >= '" + s + "' and ").orElse("");
        hqlWhereClause += maxCrewSize.map(s -> "crewSize <= '" + s + "' and ").orElse("");
        hqlWhereClause += minRating.map(s -> "rating >= '" + s + "' and ").orElse("");
        hqlWhereClause += maxRating.map(s -> "rating <= '" + s + "' and ").orElse("");

        if (!hqlWhereClause.isEmpty()) {
            hqlWhereClause = " where " + hqlWhereClause;
            hqlWhereClause = hqlWhereClause.substring(0, hqlWhereClause.lastIndexOf(" and "));
        }

        String hqlOrderBy = order.map(s -> " order by " + s.getFieldName()).orElse("");

        String hql = hqlFrom + hqlWhereClause + hqlOrderBy;

        return hql;
    }

    @Override
    public List<Ship> getAll(String hql, Optional<Integer> pageNumber, Optional<Integer> pageSize) {
        Integer pSize = pageSize.get();
        Integer pNumber = pageNumber.get();

        Query<Ship> query = (Query<Ship>) entityManager.createQuery(hql);
        query.setMaxResults(pSize);
        query.setFirstResult(pNumber * pSize);

        List<Ship> ships = query.getResultList();

        return ships;
    }

    @Override
    public Integer getCount(String hql) {

        Object result = entityManager.createQuery("select count(*) " + hql).getSingleResult();
        Integer integer = Integer.parseInt(result.toString());
        return integer;
    }
}
