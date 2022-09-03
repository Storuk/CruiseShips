package Entities;

import Enums.CruiseStatusEnum;
import Enums.UserRoleEnum;

import java.math.BigDecimal;
import java.sql.Date;

public class Cruise extends Ships {
    private int id;
    private BigDecimal price;
    private Date start_cruise_date;
    private Date end_cruise_date;
    private String cruise_name;
    private int places;
    private String image;
    private int duration;
    private int ship_id;
    private CruiseStatusEnum statuse;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Cruise() {
        super();
        this.id = id;
        this.price = price;
        this.start_cruise_date = start_cruise_date;
        this.end_cruise_date = end_cruise_date;
        this.cruise_name = cruise_name;
        this.places = places;
        this.image = image;
        this.duration = duration;
        this.statuse = statuse;
        this.ship_id = ship_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getStart_cruise_date() {
        return start_cruise_date;
    }

    public void setStart_cruise_date(Date start_cruise_date) {
        this.start_cruise_date = start_cruise_date;
    }

    public Date getEnd_cruise_date() {
        return end_cruise_date;
    }

    public void setEnd_cruise_date(Date end_cruise_date) {
        this.end_cruise_date = end_cruise_date;
    }

    public String getCruise_name() {
        return cruise_name;
    }

    public void setCruise_name(String cruise_name) {
        this.cruise_name = cruise_name;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CruiseStatusEnum getStatuse() {
        return statuse;
    }

    public void setStatuse(CruiseStatusEnum statuse) {
        this.statuse = statuse;
    }

    public int getShip_id() {
        return ship_id;
    }

    public void setShip_id(int ship_id) {
        this.ship_id = ship_id;
    }
}
