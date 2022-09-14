package Dao;

import Entities.Cruise;
import Entities.Ships;
import Enums.CruiseStatusEnum;
import connection.ConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CruiseDao {

    private static ConnectionManager cm = ConnectionManager.getInstance();

    public List<Cruise> getAllCruises(){
        List<Cruise> cruises = new ArrayList<Cruise>();
        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from cruise where statuse = ?")) {
            pst.setInt(1, CruiseStatusEnum.REGISTERED.ordinal());
            SelectCruise(cruises, pst);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return cruises;
    }

    public List<Cruise> getCruisesByFilters(BigDecimal min_price, BigDecimal max_price, Date date, int duration){
        List<Cruise> cruises = new ArrayList<Cruise>();

        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from cruise WHERE price BETWEEN ? AND ? AND start_cruise >= ? AND duration >= ? and statuse = ?")) {
            pst.setBigDecimal(1,min_price);
            pst.setBigDecimal(2,max_price);
            pst.setDate(3,date);
            pst.setInt(4,duration);
            pst.setInt(5, CruiseStatusEnum.REGISTERED.ordinal());
            SelectCruise(cruises, pst);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return cruises;
    }

    public static BigDecimal maxPrice() {
        BigDecimal max = new BigDecimal(0);
        try(Connection con = cm.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT price FROM cruise where statuse = 0")){
            if(rs.next()){
                max = rs.getBigDecimal(1);
            }
            while(rs.next()){
                if(max.compareTo(rs.getBigDecimal(1)) < 0){
                    max = rs.getBigDecimal(1);
                }
            }
            return max;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static BigDecimal minPrice() {
        BigDecimal min = new BigDecimal(0);
        try(Connection con = cm.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT price FROM cruise where statuse = 0")){
            if(rs.next()){
                min = rs.getBigDecimal(1);
            }
            while(rs.next()){
                if(min.compareTo(rs.getBigDecimal(1)) > 0){
                    min = rs.getBigDecimal(1);
                }
            }
            return min;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cruise> getAllCruisesForAdmin(){
        List<Cruise> cruises = new ArrayList<Cruise>();
        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from cruise")) {
            SelectCruise(cruises, pst);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return cruises;
    }

    public List<Cruise> getCruisesByFiltersForAdmin(BigDecimal min_price, BigDecimal max_price, Date date, int duration){
        List<Cruise> cruises = new ArrayList<Cruise>();

        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from cruise WHERE price BETWEEN ? AND ? AND start_cruise >= ? AND duration >= ?")) {
            pst.setBigDecimal(1,min_price);
            pst.setBigDecimal(2,max_price);
            pst.setDate(3,date);
            pst.setInt(4,duration);
            SelectCruise(cruises, pst);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return cruises;
    }

    private void SelectCruise(List<Cruise> cruises, PreparedStatement pst) throws SQLException, ClassNotFoundException {
        ResultSet rs =  pst.executeQuery();
        while (rs.next()){
            Cruise cruise = new Cruise();
            cruise.setId(rs.getInt("id"));
            int shipId = rs.getInt("ship_id");
            Ships ship = ShipsDao.selectShip(shipId);
            cruise.setPassenger_capacity(ship.getPassenger_capacity());
            cruise.setRoute(ship.getRoute());
            cruise.setPrice(rs.getBigDecimal("price"));
            cruise.setPorts_number(ship.getPorts_number());
            cruise.setStart_cruise_date(rs.getDate("start_cruise"));
            cruise.setEnd_cruise_date(rs.getDate("end_cruise"));
            cruise.setCruise_name(rs.getString("cruise_name"));
            cruise.setShip_name(ship.getShip_name());
            cruise.setPlaces(rs.getInt("places"));
            cruise.setImage(rs.getString("image"));
            cruise.setDuration(rs.getInt("duration"));
            if(rs.getInt("statuse") == 0){
                cruise.setStatuse(CruiseStatusEnum.IN_PROGRESS);
            }
            if(rs.getInt("statuse") == 4){
                cruise.setStatuse(CruiseStatusEnum.COMPLETED);
            }
            cruises.add(cruise);
        }
    }

    public static int addCruise(Cruise cruise) throws ClassNotFoundException {
        String INSERT_INTO_USERS = "INSERT INTO cruise ( price, start_cruise, end_cruise, cruise_name, ship_id, places, image, duration, statuse) VALUES  ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try(Connection con = cm.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_INTO_USERS))
        {
            preparedStatement.setBigDecimal(1,cruise.getPrice());
            preparedStatement.setDate(2,cruise.getStart_cruise_date());
            preparedStatement.setDate(3,cruise.getEnd_cruise_date());
            preparedStatement.setString(4,cruise.getCruise_name());
            preparedStatement.setInt(5,cruise.getShip_id());
            preparedStatement.setInt(6,cruise.getPlaces());
            preparedStatement.setString(7, cruise.getImage());
            preparedStatement.setInt(8, cruise.getDuration());
            preparedStatement.setInt(9, cruise.getStatuse().ordinal());
            result = preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }


    public static void updateCruise(Cruise cruise, int id) throws ClassNotFoundException {
        String INSERT_INTO_USERS = "UPDATE cruise SET price = ?, start_cruise = ?, end_cruise = ?, cruise_name = ?, ship_id = ?, places = ?, duration = ? where id = ?";
        try(Connection con = cm.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_INTO_USERS))
        {
            preparedStatement.setBigDecimal(1,cruise.getPrice());
            preparedStatement.setDate(2,cruise.getStart_cruise_date());
            preparedStatement.setDate(3,cruise.getEnd_cruise_date());
            preparedStatement.setString(4,cruise.getCruise_name());
            preparedStatement.setInt(5,cruise.getShip_id());
            preparedStatement.setInt(6,cruise.getPlaces());
            preparedStatement.setInt(7, cruise.getDuration());
            preparedStatement.setInt(8, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Cruise date_validation(Date start_date_cruise, Date end_date_cruise, int ship_id) throws ClassNotFoundException {
        Cruise cruise = null;

        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from cruise where (? < start_cruise and start_cruise < ? or ? < end_cruise and end_cruise < ? or start_cruise < ? < end_cruise or start_cruise < ? < end_cruise) and ship_id = ?")) {
            pst.setDate(1, start_date_cruise);
            pst.setDate(2, end_date_cruise);
            pst.setDate(3, start_date_cruise);
            pst.setDate(4, end_date_cruise);
            pst.setDate(5, start_date_cruise);
            pst.setDate(6, end_date_cruise);
            pst.setInt(7, ship_id);
            try(ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    cruise = new Cruise();
                    cruise.setShip_name("ship_name");
                    cruise.setStart_cruise_date(rs.getDate("start_cruise"));
                    cruise.setEnd_cruise_date(rs.getDate("end_cruise"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cruise;
    }

    public static void UpdateMinusCruisePlaces(int id, int quantity) {
        try(Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("UPDATE cruise SET places = places - ? where id = ?");
            pst.setInt(1, quantity);
            pst.setInt(2, id);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }

    public static void UpdatePlusCruisePlaces(int id, int quantity) {
        try(Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("UPDATE cruise SET places = places + ? where id = ?");
            pst.setInt(1, quantity);
            pst.setInt(2, id);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }

    public static void updateStatusCompleted() {
        java.util.Date utilPackageDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilPackageDate.getTime());
        try(Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("UPDATE cruise SET statuse = ? where end_cruise = ? and statuse = ?");
            pst.setInt(1, CruiseStatusEnum.COMPLETED.ordinal());
            pst.setDate(2, date);
            pst.setInt(3, CruiseStatusEnum.REGISTERED.ordinal());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean cruiseNameCheck(String cruise_name) throws ClassNotFoundException {
        boolean status = false;

        try(Connection con = cm.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("select * from cruise where cruise_name = ?"))
        {
            preparedStatement.setString(1, cruise_name);
            System.out.println(preparedStatement);
            try(ResultSet rs = preparedStatement.executeQuery()) {
                status = rs.next();
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void deleteCruise(int id){
        try(Connection connection = cm.getConnection();
            PreparedStatement pst = connection.prepareStatement("delete from cruise where id=?")){
            pst.setInt(1,id);
            pst.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Cruise getSingleProduct(int id) {
        Cruise row = null;
        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from cruise where id=?")) {
            pst.setInt(1, id);
            try(ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    row = new Cruise();
                    row.setId(rs.getInt("id"));
                    row.setPrice(rs.getBigDecimal("price"));
                    row.setStart_cruise_date(rs.getDate("start_cruise"));
                    row.setEnd_cruise_date(rs.getDate("end_cruise"));
                    row.setCruise_name(rs.getString("cruise_name"));
                    row.setShip_id(rs.getInt("ship_id"));
                    row.setImage(rs.getString("image"));
                    row.setDuration(rs.getInt("duration"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return row;
    }



    public static BigDecimal maxPriceForAdmin() {
        BigDecimal max = new BigDecimal(0);
        try(Connection con = cm.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT price FROM cruise")){
            if(rs.next()){
                max = rs.getBigDecimal(1);
            }
            while(rs.next()){
                if(max.compareTo(rs.getBigDecimal(1)) < 0){
                    max = rs.getBigDecimal(1);
                }
            }
            return max;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static BigDecimal minPriceForAdmin() {
        BigDecimal min = new BigDecimal(0);
        try(Connection con = cm.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT price FROM cruise")){
            if(rs.next()){
                min = rs.getBigDecimal(1);
            }
            while(rs.next()){
                if(min.compareTo(rs.getBigDecimal(1)) > 0){
                    min = rs.getBigDecimal(1);
                }
            }
            return min;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
