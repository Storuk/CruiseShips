package Dao;

import Entities.User;
import connection.ConnectionManager;


import java.math.BigDecimal;
import java.sql.*;

public class UserDao {
    private static ConnectionManager cm = ConnectionManager.getInstance();
    public static User validate(String username,String password) throws ClassNotFoundException {
        User user = null;

        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from users where uname = ? and upassword = ? ")) {
            pst.setString(1, username);
            pst.setString(2, password);

            System.out.println(pst);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("uname"));
                user.setFirstName(rs.getString("ufirstname"));
                user.setLastName(rs.getString("ulastname"));
                user.setEmail(rs.getString("uemail"));
                user.setPassword(rs.getString("upassword"));
                user.setScore(rs.getBigDecimal("uscore"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static String authenticateUser(User user) throws ClassNotFoundException {
        String userName = user.getUsername();
        String password = user.getPassword();
        String userNameDB = "";
        String passwordDB = "";
        String roleDB = "";
        try(Connection con = cm.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select uname,upassword,roles from users"))
        {
            while(resultSet.next())
            {
                userNameDB = resultSet.getString("uname");
                passwordDB = resultSet.getString("upassword");
                roleDB = resultSet.getString("roles");
                if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("0"))
                    return "User_Role";
                else if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("1"))
                    return "Admin_Role";
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return "Invalid user credentials";
    }

    public static int registerUser(User user) throws ClassNotFoundException {
        int result = 0;
        try(Connection con = cm.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO users ( uname, ufirstname, ulastname, uemail, upassword, uscore, roles) VALUES  ( ?, ?, ?, ?, ?, ?, ?)"))
        {
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getFirstName());
            preparedStatement.setString(3,user.getLastName());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setString(5,user.getPassword());
            preparedStatement.setBigDecimal(6,user.getScore());
            preparedStatement.setInt(7,user.getRole().ordinal());
            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public static int AddMoney(int id, BigDecimal new_balance) throws ClassNotFoundException, SQLException {
        int i = 0;
        System.out.println(new_balance);
        try(Connection con = cm.getConnection();
            PreparedStatement pst = con.prepareStatement("UPDATE users SET uscore = uscore + ? where id = ?")){
            pst.setBigDecimal(1, new_balance);
            pst.setInt(2, id);
            i = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public static boolean check(User user) throws ClassNotFoundException {
        boolean status = false;

        try(Connection con = cm.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("select * from users where uname = ?"))
        {
            preparedStatement.setString(1, user.getUsername());
            System.out.println(preparedStatement);
            try(ResultSet rs = preparedStatement.executeQuery()) {
                status = rs.next();
            }

        }
        catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
        }
        return status;
    }

}











/*public static boolean validate(User user) throws ClassNotFoundException {
        boolean status = false;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cruiseships?useSSL=false&serverTimezone=UTC", "root","sumsungj3");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = con
                     .prepareStatement("select * from users where uname = ? and upassword = ? ")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
        }
        return status;
    }*/
    /*public static boolean validateAdmin(User user) throws ClassNotFoundException {
        boolean status = false;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cruiseships?useSSL=false&serverTimezone=UTC", "root","sumsungj3");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = con
                     .prepareStatement("select roles from users where uname = ? and upassword = ? and roles = ?")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, UserRoleEnum.ADMIN.ordinal());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
        }
        return status;
    }*/