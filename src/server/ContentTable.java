package server;

import server.entity.CatalogRecord;
import server.entity.InsertRecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContentTable extends DataTable implements ResultFromTable {
    public ContentTable(Statement statement, DatabaseConnection mydbc) {
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
            e.printStackTrace();
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
