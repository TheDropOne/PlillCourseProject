package client;

import client.entity.CatalogRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by philip on 29.11.17.
 */
public class UserMenu extends JFrame {

    private JButton viewButton;
    private JButton addButton;

    private JPanel panel;

    private JScrollPane contentScroll;
    private JScrollPane ordersScroll;

    private ContentTable contentTable;
    private OrdersTable ordersTable;


    UserMenu(String login) throws HeadlessException {
        super("Меню пользователя, Ваш ник  - " + login);
        panel = new JPanel(null);
        
        setSize(800, 800);
        viewButton = new JButton("Просмотр");
        addButton = new JButton("Добавить");
        viewButton.setSize(230, 100);
        viewButton.setLocation(130, 60);
        addButton.setSize(230, 100);
        addButton.setLocation(430, 60);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client.outputStream.writeObject("catalog_records,");
                    ArrayList<CatalogRecord> records = (ArrayList<CatalogRecord>) Client.inputStream.readObject();
                    attachContentTable(new ContentTable(records));

                    Client.outputStream.writeObject("orders_records,");
                    ArrayList<CatalogRecord> orders = (ArrayList<CatalogRecord>) Client.inputStream.readObject();
                    attachContentTable(new ContentTable(orders));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        panel.add(viewButton);
        panel.add(addButton);
        add(panel);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void attachContentTable(ContentTable contentTable) {
        this.contentTable = contentTable;
        contentScroll = new JScrollPane(contentTable);
        contentScroll.setSize(740, 260);
        contentScroll.setLocation(30, 300);
        contentScroll.setVisible(true);
        panel.add(contentScroll);
    }


    public void attachOrdersTable(OrdersTable ordersTable) {
        this.ordersTable = ordersTable;
        ordersScroll = new JScrollPane(contentTable);
        ordersScroll.setSize(740, 160);
        ordersScroll.setLocation(30, 580);
        ordersScroll.setVisible(true);
        panel.add(ordersScroll);
    }
}
