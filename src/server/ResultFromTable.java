package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface ResultFromTable {
    ResultSet getResultFromTable();

    default ResultSet getRoleFromTable(Statement statement, String login, String password) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT id, role FROM users WHERE login = \'" + login +
                    "\' AND password = \'" + password + "\';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}



