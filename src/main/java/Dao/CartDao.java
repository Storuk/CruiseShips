package Dao;

import Entities.Cart;
import connection.ConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    private static ConnectionManager cm = ConnectionManager.getInstance();

    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> cruises = new ArrayList<>();

       try{
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    try (Connection con = cm.getConnection();
                         PreparedStatement pst = con.prepareStatement("select * from cruise where id=?")) {

                        pst.setInt(1, item.getId());

                        try( ResultSet rs = pst.executeQuery()) {
                            while (rs.next()) {
                                Cart row = new Cart();
                                row.setId(rs.getInt("id"));
                                row.setCruise_name(rs.getString("cruise_name"));
                                row.setStart_cruise_date(rs.getDate("start_cruise"));
                                row.setPrice(rs.getBigDecimal("price").multiply(BigDecimal.valueOf(item.getQuantity())));
                                row.setPlaces(rs.getInt("places"));
                                row.setQuantity(item.getQuantity());
                                cruises.add(row);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return cruises;
    }

    public BigDecimal getTotalCartPrice(ArrayList<Cart> cartList) {
        BigDecimal sum = new BigDecimal(0);
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    try (Connection con = cm.getConnection();
                         PreparedStatement pst = con.prepareStatement("select price from cruise where id=?")) {
                        pst.setInt(1, item.getId());
                        try(ResultSet rs = pst.executeQuery()){
                            while (rs.next()) {
                                sum = sum.add((rs.getBigDecimal("price").multiply(BigDecimal.valueOf(item.getQuantity()))));
                            }
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }
}
