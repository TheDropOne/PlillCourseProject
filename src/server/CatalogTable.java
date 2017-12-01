package server;

import client.entity.CatalogRecord;
import client.entity.InsertRecord;

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
//        try {
//            String insertStr = "UPDATE content SET id=" + number;
//            int done = statement.executeUpdate(insertStr);
//            return (done != 0) ? "Успешно удалено" : "Ошибка удаления!";
//        } catch (Exception e) {
//            System.err.println("Ошибка удаления данных");
//            return "fail";
//        }
        return null;
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
