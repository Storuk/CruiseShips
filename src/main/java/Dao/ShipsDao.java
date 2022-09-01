package Dao;

import Entities.Ships;
import Entities.User;
import connection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipsDao {
    private static ConnectionManager cm = ConnectionManager.getInstance();

    public static Ships selectShip(int id) throws ClassNotFoundException {
        Ships ships = null;

        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from ships where id = ?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                ships = new Ships();
                ships.setShip_name(rs.getString("ship_name"));
                ships.setPassenger_capacity(rs.getInt("passenger_capacity"));
                ships.setRoute(rs.getString("route"));
                ships.setPorts_number(rs.getInt("ports_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ships;
    }
}
