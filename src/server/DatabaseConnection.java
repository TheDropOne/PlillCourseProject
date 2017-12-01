package server;

/**
 * Created by philip on 29.11.17.
 */
import java.sql.*;
//import com.mysql.fabric.jdbc.FabricMySQLDriver;

public class DatabaseConnection {
    private Connection myDBConnection;
//    private static final String URL = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
//            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public DatabaseConnection() {
    }

    public void initialization() {
        try {
            //Driver driver=new FabricMySQLDriver();
            Class.forName("com.mysql.jdbc.Driver");
            myDBConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursach", "phillip", "password");
            if(!myDBConnection.isClosed()) {
                System.out.println("Соединение с БД установлено....");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
    public void closeConnection(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }
    public Connection getMyConnection() {

        return myDBConnection;
    }

    public void closeConnection(ResultSet result) {
        if (result != null) {
            try {
                result.close();
            } catch (Exception e) {
            }
        }
    }

    public void destroyConnection() {
        if (myDBConnection != null) {
            try {
                myDBConnection.close();
            } catch (Exception e) {
            }
        }
    }

}
