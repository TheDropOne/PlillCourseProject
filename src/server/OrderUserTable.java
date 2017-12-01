package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderUserTable extends DataTable implements ResultFromTable {
    public OrderUserTable(Statement statement, DatabaseConnection mydbc) {
        super(statement, mydbc);
    }

    public ResultSet getResultFromTable() {
        ResultSet result = null;
        try {
            result = statement.executeQuery("SELECT * FROM orders");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
        return result;
    }

    public String addInTable(String[] data) {
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

    public String deleteFromTable(String ID) {
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


    public String readAllRecords() {
        ResultSet result = getResultFromTable();
        try {
            while (result.next()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(result.getString("idOrders"));
                stringBuilder.append(",");
                stringBuilder.append(result.getString("userLogin"));
                stringBuilder.append(",");
                stringBuilder.append(result.getString("idPupil"));
                stringBuilder.append(",");
                stringBuilder.append(result.getString("surname"));
                stringBuilder.append(",");

                return stringBuilder.toString();
            }
        } catch (Exception e) {
            System.err.println("Хьюстон, у нас проблемы!");
        } finally {
            databaseConnection.closeConnection(result);
        }
        return "";
    }
}
