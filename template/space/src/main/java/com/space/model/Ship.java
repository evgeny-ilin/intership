package com.space.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name = "ship")
public class Ship implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String planet;
    @Enumerated(EnumType.STRING)
    private ShipType shipType;
    private Date prodDate;
    private Boolean isUsed;
    private Double speed;
    private Integer crewSize;
    private Double rating;

    public Ship(){}

    public Ship(Long id, String name, String planet, ShipType shipType, Date prodDate, Boolean isUsed, Double speed, Integer crewSize, Double rating) {
        this.id = id;
        this.name = name;
        this.planet = planet;
        this.shipType = shipType;
        this.prodDate = prodDate;
        this.isUsed = isUsed;
        this.speed = speed;
        this.crewSize = crewSize;
        this.rating = rating;
    }

    public boolean checkParams() {
        if (name == null && planet == null && shipType == null && prodDate == null && isUsed == null || speed == null && crewSize == null)
            return false;

        if (name == null || name.isEmpty() || name.length() > 50) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Name is not valid: " + name);
        if (planet == null || planet.isEmpty() || planet.length() > 50) throw new  ResponseStatusException(HttpStatus.BAD_REQUEST,"planet is not valid: " + planet);
        if (shipType == null || shipType.name() == null || shipType.name().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"shipType is not valid: " + shipType);

        //Check prodDate
        Calendar calStart = new GregorianCalendar();
        calStart.set(Calendar.YEAR,2800);
        Calendar calEnd = new GregorianCalendar();
        calEnd.set(Calendar.YEAR,3019);
        if (prodDate == null || prodDate.after(calEnd.getTime()) || prodDate.before(calStart.getTime())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"prodDate is not valid: " + prodDate);

        if (isUsed == null) isUsed = false;
        //Check speed
        if (speed == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"speed is null");
        speed = new BigDecimal(speed).setScale(2,RoundingMode.HALF_UP).doubleValue();

        if (speed < 0.01 || speed > 0.99) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"speed is not valid: " + speed);

        if (crewSize == null || crewSize < 1 || crewSize > 9999) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"crewSize is not valid: " + crewSize);
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlanet() {
        return planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public Boolean isUsed() {
        return isUsed;
    }

    public Double getSpeed() {
        return speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public Double getRating() {
        return rating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public void setRating() {
        double k = isUsed ? 0.5 : 1;
        double r = (80 * speed * k) / (3019 - prodDate.getYear() + 1);
        this.rating = new BigDecimal(r).setScale(2,RoundingMode.HALF_UP).doubleValue();
    }

    public static void checkId(Long id) {
        if (id == null || id < 1) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id is not valid: "+id);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", planet='" + planet + '\'' +
                ", shipType=" + shipType +
                ", prodDate=" + prodDate +
                ", isUsed=" + isUsed +
                ", speed=" + speed +
                ", crewSize=" + crewSize +
                ", rating=" + rating +
                '}';
    }
}
