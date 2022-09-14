package Dao;

import Entities.Ships;
import Entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipsDaoTest {
    ShipsDao shipsDao = new ShipsDao();
    @Test
    void selectShip() throws ClassNotFoundException {
        int id = 1;
        String shipName = "Symphony of the Seas";
        String passengerCapacity = "120";
        String route = "Rim to Barcelona";
        String portsNumber = "6";
        Ships ships = new Ships();
        ships.setShip_name(shipName);
        ships.setPassenger_capacity(Integer.parseInt(passengerCapacity));
        ships.setRoute(route);
        ships.setPorts_number(Integer.parseInt(portsNumber));
        assertTrue(ships.equals(shipsDao.selectShip(id)));
    }
}