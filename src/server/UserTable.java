package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTable extends DataTable implements ResultFromTable {

    UserTable(Statement statement, DatabaseConnection mydbc) {
        super(statement, mydbc);
    }

    @Override
    public ResultSet getResultFromTable() {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM users ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public String registration(String login, String password) {
        ResultSet resultSet = null;
        try {
            String query = "INSERT INTO users (login,password,role) VALUES (" +
                    quotate(login)+", " + quotate(password) + ", 2);";
            int done1 = statement.executeUpdate(query);
            return (done1 != 0) ? "successreg," : "Ошибка добавления пользователя в систему!";
        } catch (SQLException e) {
            return "An registration error occured";
        } catch (Exception ex){
            return "An internal error occured";
        }
    }


    String checkLogin(String login, String password) {
        ResultSet rs = getRoleFromTable(statement, login, password);
        try {
            if (rs.next() && rs.getString("role") != null && !rs.getString("role").isEmpty()) {
                return "success" + "," + rs.getString("role") + "," + rs.getString("id");
            } else {
                return "fail";
            }
        } catch (SQLException e) {
            System.err.println("Произошло исключение в таблице пользователя");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Произошло исключение");
            e.printStackTrace();
        } finally {
            databaseConnection.closeConnection(rs);
        }
        return "";
    }
}
