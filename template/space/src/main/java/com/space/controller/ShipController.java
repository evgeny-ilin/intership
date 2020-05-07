package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ShipController {

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

        List<Ship> ships = new ArrayList<>();

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
        return 2;
    }

    //TODO Переделать на RequestBody
    @RequestMapping(value = "/rest/ships", method = RequestMethod.POST)
    public Ship createShip (@RequestParam String name,
                            @RequestParam String planet,
                            @RequestParam ShipType shipType,
                            @RequestParam Long prodDate,
                            @RequestParam (value = "isUsed", required = false, defaultValue = "false") Boolean isUsed,
                            @RequestParam Double speed,
                            @RequestParam Integer crewSize)
    {
        return new Ship(3,
                name,
                planet,
                shipType,
                new Date(prodDate),
                isUsed,
                speed,
                crewSize,
                12.0);
    }

    @RequestMapping(value = "/rest/ships/{id}", method = RequestMethod.GET)
    public Ship getShip(@PathVariable Integer id)
    {
        return new Ship(2,
                "Бригантина",
                "Луна",
                ShipType.MERCHANT,
                new Date(),
                true,
                0.05,
                100,
                3.01);
    }

    //TODO Переделать на RequestBody
    @RequestMapping(value = "/rest/ships/{id}", method = RequestMethod.POST)
    public Ship updateShip(@PathVariable Integer id,
                           @RequestParam String name,
                           @RequestParam String planet,
                           @RequestParam ShipType shipType,
                           @RequestParam Long prodDate,
                           @RequestParam Boolean isUsed,
                           @RequestParam Double speed,
                           @RequestParam Integer crewSize)
    {
        return new Ship(3,
                name,
                planet,
                shipType,
                new Date(prodDate),
                isUsed,
                speed,
                crewSize,
                12.0);
    }

    //TODO Разобраться почему не удаляет в интерфейсе
    @RequestMapping(value = "/rest/ships/{id}", method = RequestMethod.DELETE)
    public void deleteShip(@PathVariable Integer id) {

    }

}
