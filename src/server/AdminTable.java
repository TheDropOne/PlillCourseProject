package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by philip on 30.11.17.
 */
public class AdminTable extends DataTable implements ResultFromTable {
    public AdminTable(Statement statement, DatabaseConnection mydbc) {

        super(statement, mydbc);
    }

    @Override
    public ResultSet getResultFromTable() {
        ResultSet result = null;
        try {
            result = statement.executeQuery("SELECT * FROM admin");
        } catch (SQLException e) {
        }
        return result;
    }

    public String checkLogin(String login, String password) {
        ResultSet rs = getResultFromTable();
        int flag = 0;
        String tableLogin = "";
        String tablePassword = "";
        try {
            while (rs.next()) {
                tableLogin = rs.getString("login");
                tablePassword = rs.getString("password");
                if (tableLogin.equals(login) && tablePassword.equals(password)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                return "success";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            System.out.println("Exception in Table of admin");
            return "";
        } finally {
            mydbc.closeConnection(rs);
        }
    }
}
