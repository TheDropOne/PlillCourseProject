package server;

import client.entity.CatalogRecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CatalogTable extends DataTable implements ResultFromTable {
    public CatalogTable(Statement statement, DatabaseConnection mydbc) {
        super(statement, mydbc);
    }

    public ResultSet getResultFromTable() {
        ResultSet result = null;
        try {
            result = statement.executeQuery("SELECT * FROM content");
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


    public List<CatalogRecord> readAllRecords() {
        ResultSet result = getResultFromTable();
        List<CatalogRecord> records = new ArrayList<>();
        try {
            while (result.next()) {
                CatalogRecord record = new CatalogRecord(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("firm"),
                        result.getInt("year_of_publishing"),
                        result.getInt("price"),
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
}
