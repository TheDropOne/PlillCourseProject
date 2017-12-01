package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by philip on 30.11.17.
 */
public class OrderUserTable extends DataTable implements ResultFromTable{
    public OrderUserTable(Statement statement, DatabaseConnection mydbc) {
        super(statement, mydbc);
    }

    public ResultSet getResultFromTable() {
        ResultSet result = null;
        try {
            result = statement.executeQuery("SELECT * FROM orders");
        } catch (SQLException ex) {
        }
        return result;
    }

    public String AddInTable(String[] data) {
        String insertStr = "";
        try {
            insertStr = "INSERT INTO orders (userLogin,idPupil,surname) VALUES ("
                    + quotate(data[1]) + ","
                    + quotate(data[2]) + ","
                    + quotate(data[3]) + ")";
            int done = statement.executeUpdate(insertStr);
            return "success";
        } catch (Exception ex) {
            System.err.println("Ошибка ввода данных");
            return "fail";
        }
    }

    public String DeleteFromTable(String ID) {
        String insertStr = "";
        try {
            insertStr = "DELETE FROM orders WHERE idOrders=" + ID;
            int done = statement.executeUpdate(insertStr);
            return "success";
        } catch (Exception e) {
            System.err.println("Ошибка удаления данных");
            return "fail";
        }
    }

    public String FilterInTable(String FilterColumn, String FilterValue) {
        ResultSet result = null;
        String message = "";
        try {
            result = statement.executeQuery("select * from orders where userLogin like '%" + FilterValue + "%'");
            while (result.next()) {
                message += result.getString("idOrders") + ",";
                message += result.getString("userLogin") + ",";
                message += result.getString("idPupil") + ",";
                message += result.getString("surname") + ",";
            }
            if (message.equals("")) {
                return "Fail";
            } else {
                return message;
            }
        } catch (SQLException e) {
            System.err.println("Exception in Table of orders");
            return "";
        } finally {
            mydbc.closeConnection(result);
        }
    }

    public String ReadAllRecord() {
        ResultSet result = getResultFromTable();
        String message = "";
        try {
            while (result.next()) {
                message += result.getString("idOrders") + ",";
                message += result.getString("userLogin") + ",";
                message += result.getString("idPupil") + ",";
                message += result.getString("surname") + ",";
            }
            return message;
        } catch (Exception e) {
            System.out.println("Exception in Table of orders");
            return "";
        } finally {
            mydbc.closeConnection(result);
        }
    }
}
