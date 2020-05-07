//package com.space.service;
//
//import com.space.model.Ship;
//import com.space.dao.ShipDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class ShipServiceImpl implements ShipService {
//
//    @Autowired
//    private ShipDao shipDao;
//
//    @Transactional
//    @Override
//    public void save(Ship ship) {
//        shipDao.save(ship);
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public List<Ship> list() {
//        return shipDao.list();
//    }
//}
