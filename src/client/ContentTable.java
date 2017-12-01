package client;

import client.entity.CatalogRecord;
import server.CatalogTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContentTable extends JTable {

    public ContentTable(List<CatalogRecord> list) {
        DefaultTableModel model = new DefaultTableModel();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                model.addColumn(rsmd.getColumnName(i));
            }
            model.addColumn("id");
            model.addColumn("name");
            model.addColumn("firm");
            model.addColumn("yearOfPublishing");
            model.addColumn("price");
            model.addColumn("amount");
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    v.add(rs.getString(i));
                }
                model.addRow(v);
            }
            this.setModel(model);
            this.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
        } catch (SQLException ex) {
            Logger.getLogger(ContentTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
