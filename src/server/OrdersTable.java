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


    public ResultSet getResultFromTable(int id) {
        ResultSet result = null;
        try {
            result = statement.executeQuery("SELECT user_id, users.login, content_id, content.name, orders.amount FROM orders " +
                    "LEFT JOIN users ON orders.user_id = users.id " +
                    "LEFT JOIN content ON orders.content_id = content.id " +
                    "WHERE user_id =" + String.valueOf(id));
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
        return result;
    }

    public List<OrderRecord> readAllRecords(int userId) {
        ResultSet result = getResultFromTable(userId);
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
        ResultSet result;
        int done = 0;
        String[] splitted = data.split(" ");
        try {
            String insertStr = "INSERT INTO orders VALUES ("
                    + Integer.parseInt(splitted[1]) + "," // user_id
                    + Integer.parseInt(splitted[0]) + "," // content_id
                    + 1 + ")"; // 1 Заказ
            done = statement.executeUpdate(insertStr);

            if (done != 0) {
                result = statement.executeQuery("SELECT content.amount FROM content WHERE id= " +
                        Integer.parseInt(splitted[0]) + ";");
                result.next();
                int amount = result.getInt("amount") - 1;
                String updateStr = "UPDATE content SET " +
                        " amount=" + amount +
                        " WHERE id=" + Integer.parseInt(splitted[0]) + ";";
                done = statement.executeUpdate(updateStr);
                return (done != 0) ? "Успешно добавлено" : "Ошибка добавления!";
            } else {
                return "Ошибка добавления!";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Ошибка базы данных";
        }
    }

    @Override
    public ResultSet getResultFromTable() {
        return null;
    }
}
