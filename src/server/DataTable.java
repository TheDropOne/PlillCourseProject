package server;

import java.sql.Statement;

abstract class DataTable {
    Statement statement;
    DatabaseConnection databaseConnection;

    DataTable(Statement statement, DatabaseConnection databaseConnection) {
        this.statement = statement;
        this.databaseConnection = databaseConnection;
    }

    String quotate(String content) {
        return "'" + content + "'";
    }

}
