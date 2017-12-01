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
            model.addColumn("id");
            model.addColumn("name");
            model.addColumn("firm");
            model.addColumn("yearOfPublishing");
            model.addColumn("price");
            model.addColumn("amount");
            for (CatalogRecord record : list) {
                Vector v = new Vector();

                v.add(record.getId());
                v.add(record.getName());
                v.add(record.getFirm());
                v.add(record.getYearOfPublishing());
                v.add(record.getPrice());
                v.add(record.getAmount());

                model.addRow(v);
            }
            this.setModel(model);
            this.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
    }
}
