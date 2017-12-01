package server;

/**
 * Created by philip on 30.11.17.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTable extends DataTable implements ResultFromTable {

    public UserTable(Statement statement, DatabaseConnection mydbc) {

        super(statement, mydbc);
    }

    @Override
    public ResultSet getResultFromTable() {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM users ");
        } catch (SQLException e) {
        }
        return rs;
    }


    String checkLogin(String login, String password) {
        ResultSet rs = getRoleFromTable(statement, login, password);
        try {
            if (rs.next() && rs.getString("role") != null && !rs.getString("role").isEmpty()) {
                return "success" + "," + rs.getString("role");
            } else {
                return "fail,3";
            }
        } catch (SQLException e){
            System.err.println("Произошло исключение в таблице пользователя");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Произошло исключение");
            e.printStackTrace();
        } finally {
            mydbc.closeConnection(rs);
        }
        return "";
    }
}