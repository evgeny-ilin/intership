package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ShipController {

    @Autowired
    private ShipService shipService;

    @RequestMapping(value = "/rest/ships", method = RequestMethod.GET)
    public List<Ship> getShips(@RequestParam Optional<String> name,
                               @RequestParam Optional<String> planet,
                               @RequestParam Optional<ShipType> shipType,
                               @RequestParam Optional<Long> after,
                               @RequestParam Optional<Long> before,
                               @RequestParam Optional<Boolean> isUsed,
                               @RequestParam Optional<Double> minSpeed,
                               @RequestParam Optional<Double> maxSpeed,
                               @RequestParam Optional<Integer> minCrewSize,
                               @RequestParam Optional<Integer> maxCrewSize,
                               @RequestParam Optional<Double> minRating,
                               @RequestParam Optional<Double> maxRating,
                               @RequestParam Optional<ShipOrder> shipOrder,
                               @RequestParam (defaultValue = "0") Integer pageNumber,
                               @RequestParam (defaultValue = "3") Integer pageSize)
    {
        return shipService.getAll(pageNumber,pageSize);
    }

    @RequestMapping(value = "/rest/ships/count", method = RequestMethod.GET)
    public Integer shipsCount (@RequestParam Optional<String> name,
                               @RequestParam Optional<String> planet,
                               @RequestParam Optional<ShipType> shipType,
                               @RequestParam Optional<Long> after,
                               @RequestParam Optional<Long> before,
                               @RequestParam Optional<Boolean> isUsed,
                               @RequestParam Optional<Double> minSpeed,
                               @RequestParam Optional<Double> maxSpeed,
                               @RequestParam Optional<Integer> minCrewSize,
                               @RequestParam Optional<Integer> maxCrewSize,
                               @RequestParam Optional<Double> minRating,
                               @RequestParam Optional<Double> maxRating)
    {
        return shipService.getCount();
    }

    @RequestMapping(value = "/rest/ships", method = RequestMethod.POST)
    public Ship createShip (@RequestBody Ship ship)
    {
        return shipService.create(ship);
    }

    @RequestMapping(value = "/rest/ships/{id}", method = RequestMethod.GET)
    public Ship getShip(@PathVariable Long id)
    {
        return shipService.getById(id);
    }


    @RequestMapping(value = "/rest/ships/{id}", method = RequestMethod.POST)
    public Ship updateShip(@PathVariable Long id, @RequestBody Ship ship)
    {
        ship.setId(id);
        return shipService.update(ship);
    }

    @RequestMapping(value = "/rest/ships/{id}", method = RequestMethod.DELETE)
    public void deleteShip(@PathVariable Long id) {
        shipService.deleteById(id);
    }

}
