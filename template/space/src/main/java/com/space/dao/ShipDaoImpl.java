//package com.space.dao;
//
//import com.space.model.Ship;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.TypedQuery;
//import java.util.List;
//
//@Repository
//public class ShipDaoImpl implements ShipDao {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @Override
//    public void save(Ship ship) {
//        sessionFactory.getCurrentSession().save(ship);
//    }
//
//    @Override
//    public List<Ship> list() {
//        @SuppressWarnings("unchecked")
//        TypedQuery<Ship> query = sessionFactory.getCurrentSession().createQuery("from com.space.model.Ship");
//        return query.getResultList();
//    }
//}
