package connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static DataSource dataSource = null;

    private static ConnectionManager connectionManager;

    public ConnectionManager() {
        if(dataSource == null){
            HikariConfig config = new HikariConfig("C:\\Users\\Влад\\IdeaProjects\\final_project2\\src\\main\\resources\\hikari.properties");
            dataSource = new HikariDataSource(config);
        }
    }

    public static ConnectionManager getInstance(){
        if(connectionManager == null){
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}





