package Dao;

import Entities.Cruise;
import Entities.UserOrders;
import Enums.CruiseStatusEnum;
import connection.ConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserOrdersDao {

    CruiseDao cruiseDao = new CruiseDao();
    private static ConnectionManager cm = ConnectionManager.getInstance();

    public boolean insertOrder(UserOrders model) {
        boolean result = false;
        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into orders (cruise_id, user_id, order_quantity,statusId, order_date, payment_amount, images,cruise_name) values(?,?,?,?,?,?,?,?)")) {
            pst.setInt(1, model.getId());
            pst.setInt(2, model.getU_id());
            pst.setInt(3, model.getQuantity());
            pst.setInt(4, model.getStatusId().ordinal());
            pst.setString(5, model.getDate());
            pst.setBigDecimal(6, model.getPaymentAmount());
            pst.setString(7, model.getImages());
            pst.setString(8,model.getCruise_name());
            pst.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static int PayForCruise(int id, BigDecimal sum) throws ClassNotFoundException, SQLException {
        int i = 0;
        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("UPDATE users SET uscore = uscore - ? where id = ?")) {
            pst.setBigDecimal(1, sum);
            pst.setInt(2, id);
            i = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public List<UserOrders> userOrders(int id) {
        List<UserOrders> list = new ArrayList<>();
        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from orders where user_id=? order by orders.statusId")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                    UserOrders order = new UserOrders();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setId(rs.getInt("cruise_id"));
                    order.setCruise_name(rs.getString("cruise_name"));
                    order.setPaymentAmount(rs.getBigDecimal("payment_amount"));
                    order.setQuantity(rs.getInt("order_quantity"));
                    order.setDate(rs.getString("order_date"));
                    StatusCheck(list, order, rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void StatusCheck(List<UserOrders> list, UserOrders order, ResultSet rs) throws SQLException {
        if(rs.getInt("statusId") == 1){
            order.setStatusId(CruiseStatusEnum.IN_PROGRESS);
        }
        else if(rs.getInt("statusId") == 2){
            order.setStatusId(CruiseStatusEnum.PAID);
        }
        else if(rs.getInt("statusId") == 3){
            order.setStatusId(CruiseStatusEnum.CANCELED);
        }
        else if(rs.getInt("statusId") == 6){
            order.setStatusId(CruiseStatusEnum.DELETED_BY_ADMIN);
        }
        list.add(order);
    }

    public void deleteOrder(int id) {
        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("delete from orders where order_id=?")) {
            pst.setInt(1, id);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UserOrders> userOrdersForAdmin() {
        List<UserOrders> list = new ArrayList<>();
        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from orders order by orders.statusId");
            ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                UserOrders order = new UserOrders();
                order.setOrderId(rs.getInt("order_id"));
                order.setId(rs.getInt("cruise_id"));
                order.setCruise_name(rs.getString("cruise_name"));
                order.setU_id(rs.getInt("user_id"));
                order.setPaymentAmount(rs.getBigDecimal("payment_amount"));
                order.setQuantity(rs.getInt("order_quantity"));
                order.setDate(rs.getString("order_date"));
                order.setImages(rs.getString("images"));
                StatusCheck(list, order, rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }


    public void AcceptOrder(int id) {
        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("UPDATE orders SET statusId = ? where order_id = ?")) {
            pst.setInt(1, CruiseStatusEnum.PAID.ordinal());
            pst.setInt(2,id);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }

    public void CancelOrder(int id) {
        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("UPDATE orders SET statusId = ? where order_id = ?")) {
            pst.setInt(1, CruiseStatusEnum.CANCELED.ordinal());
            pst.setInt(2,id);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }


    public static void deleteOrders(int id) {
        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("delete from orders where cruise_id=?")) {
            pst.setInt(1, id);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateDeletedStatusOrders(int id) {
        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("UPDATE orders SET statusId = ? where cruise_id = ?")) {
            pst.setInt(1, CruiseStatusEnum.DELETED_BY_ADMIN.ordinal());
            pst.setInt(2, id);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
