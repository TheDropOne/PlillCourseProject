package client;

import server.entity.OrderRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class OrdersTable extends JTable {

    public OrdersTable(List<OrderRecord> list) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("user_id");
        model.addColumn("user_name");
        model.addColumn("content_id");
        model.addColumn("content_name");
        model.addColumn("amount");
        for (OrderRecord record : list) {
            Vector v = new Vector();

            v.add(record.getUserId());
            v.add(record.getUserName());
            v.add(record.getContentId());
            v.add(record.getContent_name());
            v.add(record.getAmount());

            model.addRow(v);
        }
        this.setModel(model);
        this.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
    }
}
