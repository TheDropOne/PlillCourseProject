package server;

import server.entity.OrderRecord;
import server.entity.CatalogRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class OrdersTable extends DataTable implements ResultFromTable {

    public OrdersTable(Statement statement, DatabaseConnection mydbc) {
        super(statement, mydbc);
    }

    public ResultSet getResultFromTable() {
        ResultSet result = null;
        try {
            result = statement.executeQuery("SELECT user_id, users.login, content_id, content.name, amount FROM orders" +
                    "LEFT JOIN users ON orders.user_id = users.id " +
                    "LEFT JOIN content ON orders.content_id = content.id;");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
        return result;
    }

    public List<OrderRecord> readAllRecords() {
        ResultSet result = getResultFromTable();
        List<OrderRecord> records = new ArrayList<>();
        try {
            while (result.next()) {
                OrderRecord record = new OrderRecord(
                        result.getInt("user_id"),
                        result.getString("login"),
                        result.getInt("content_id"),
                        result.getString("name"),
                        result.getInt("amount")
                );
                records.add(record);
            }
            return records;
        } catch (Exception e) {
            System.err.println("Хьюстон, у нас проблемы!");
        } finally {
            databaseConnection.closeConnection(result);
        }
        return null;
    }

    public String addInTable(String data) {
        String[] splitted = data.split(" ");
        try {
            String insertStr = "INSERT INTO content (name, firm, year_of_publishing, price, amount) VALUES ("
                    + quotate(splitted[0]) + ","
                    + quotate(splitted[1]) + ","
                    + splitted[2] + ","
                    + splitted[3] + ","
                    + splitted[4] + ")";
            int done = statement.executeUpdate(insertStr);
            return (done != 0) ? "Успешно добавлено" : "Ошибка добавления!";
        } catch (Exception ex) {
            System.err.println("Ошибка ввода данных");
            return "fail";
        }
    }

    public String deleteFromTable(Integer number) {
        try {
            String insertStr = "DELETE FROM content WHERE id=" + number;
            int done = statement.executeUpdate(insertStr);
            return (done != 0) ? "Успешно удалено" : "Ошибка удаления!";
        } catch (Exception e) {
            System.err.println("Ошибка удаления данных");
            return "fail";
        }
    }

    public String updateRecord(String data, int number) {
        try {
            String[] splitted = data.split(" ");
            String insertStr = "UPDATE content SET " +
                    "name=" + quotate(splitted[0]) +
                    ", firm=" + quotate(splitted[1]) +
                    ", year_of_publishing=" +splitted[2] +
                    ", price=" +splitted[3] +
                    ", amount=" +  splitted[4] +
                    " WHERE id=" + number + ";";

            int done = statement.executeUpdate(insertStr);
            return (done != 0) ? "Успешно обновлено" : "Ошибка обновления!";
        } catch (Exception e) {
            System.err.println("Ошибка обновления данных");
            return "fail";
        }
    }
}
