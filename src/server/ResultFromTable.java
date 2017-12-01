package server;

/**
 * Created by philip on 30.11.17.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface ResultFromTable {
    ResultSet getResultFromTable();

    default ResultSet getRoleFromTable(Statement statement, String login, String password) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT role FROM users WHERE login = \'" + login +
                    "\' AND password = \'" + password + "\';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}

