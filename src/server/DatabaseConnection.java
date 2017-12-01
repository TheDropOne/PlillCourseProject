package server;

/**
 * Created by philip on 29.11.17.
 */

import java.sql.*;

import settings.CustomSettings;

public class DatabaseConnection {
    private Connection myDBConnection;

    public DatabaseConnection() {
    }

    public void initialization() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            myDBConnection = DriverManager.getConnection(
                    CustomSettings.hostname + CustomSettings.databaseName, // Адрес
                    CustomSettings.username, // Имя пользователя
                    CustomSettings.password // Пароль
            );
            if (!myDBConnection.isClosed()) {
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
