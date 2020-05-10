package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ShipController {

    List<Ship> ships = new ArrayList<>();

    {
        ships.add(new Ship(1,
                "Фрегат",
                "Мрас",
                ShipType.TRANSPORT,
                new Date(),
                true,
                0.01,
                10,
                5.55));
        ships.add(new Ship(2,
                "Бригантина",
                "Луна",
                ShipType.MERCHANT,
                new Date(),
                true,
                0.05,
                100,
                3.01));
    }

//    @Autowired
//    private ShipService shipService;

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
                               @RequestParam Optional<Integer> pageNumber,
                               @RequestParam Optional<Integer> pageSize)
    {
        return ships;
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
        return ships.size();
    }

    @RequestMapping(value = "/rest/ships", method = RequestMethod.POST)
    public Ship createShip (@RequestBody Ship ship)
    {
        Ship newShip = new Ship(3,
                ship.getName(),
                ship.getPlanet(),
                ship.getShipType(),
                ship.getProdDate(),
                ship.isUsed(),
                ship.getSpeed(),
                ship.getCrewSize(),
                12.0);
        ships.add(newShip);
        return newShip;
    }

    @RequestMapping(value = "/rest/ships/{id}", method = RequestMethod.GET)
    public Ship getShip(@PathVariable Integer id)
    {
        return ships.get(id - 1);
    }


    @RequestMapping(value = "/rest/ships/{id}", method = RequestMethod.POST)
    public Ship updateShip(@RequestBody Ship ship, @PathVariable String id)
    {
        //int iId = (int) ship.getId();
        id = id.replace("{", "");
        id = id.replace("}", "");

        int iId = Integer.parseInt(id) - 1;

        ships.get(iId).setName(ship.getName());
        ships.get(iId).setPlanet(ship.getPlanet());
        ships.get(iId).setShipType(ship.getShipType());
        ships.get(iId).setUsed(ship.isUsed());
        ships.get(iId).setSpeed(ship.getSpeed());
        ships.get(iId).setCrewSize(ship.getCrewSize());

        return ships.get(iId);
    }

    @RequestMapping(value = "/rest/ships/{id}", method = RequestMethod.DELETE)
    public void deleteShip(@PathVariable Integer id) {
        ships.remove(id - 1);
    }

}
