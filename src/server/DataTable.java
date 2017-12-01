package server;

import java.sql.Statement;

/**
 * Created by philip on 30.11.17.
 */
abstract public class DataTable {
    protected Statement statement;
    protected DatabaseConnection mydbc;

    public DataTable(Statement statement, DatabaseConnection mydbc) {
        this.statement = statement;
        this.mydbc = mydbc;
    }

    public String quotate(String content) {
        return "'" + content + "'";
    }

}
