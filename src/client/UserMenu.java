package client;

import client.entity.InsertRecord;
import server.entity.CatalogRecord;
import server.entity.OrderRecord;

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
    private JButton writeToFileButton;

    private JPanel panel;

    private JScrollPane contentScroll;
    private JScrollPane ordersScroll;

    private ContentTable contentTable;
    private OrdersTable ordersTable;

    private JTextField addNumber;

    private JLabel attention;

    UserMenu(String login, int id) throws HeadlessException {
        super("Меню пользователя, Ваш ник  - " + login);
        panel = new JPanel(null);

        setSize(800, 800);
        viewButton = new JButton("Просмотр");
        addButton = new JButton("Заказать номер ");
        viewButton.setSize(230, 100);
        viewButton.setLocation(130, 60);
        addButton.setSize(230, 100);
        addButton.setLocation(430, 60);
        writeToFileButton = new JButton("Записать в файл");
        writeToFileButton.setSize(230, 60);
        writeToFileButton.setLocation(230, 220);

        viewButton.addActionListener(e -> {
            try {
                Client.outputStream.writeObject("catalog_records,");
                ArrayList<CatalogRecord> records = (ArrayList<CatalogRecord>) Client.inputStream.readObject();
                attachContentTable(new ContentTable(records));

                Client.outputStream.writeObject("orders_records,"+id);
                ArrayList<OrderRecord> orders = (ArrayList<OrderRecord>) Client.inputStream.readObject();
                attachOrdersTable(new OrdersTable(orders));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        addButton.addActionListener(e -> {
            try {
                int orderNumber = Integer.parseInt(addNumber.getText());

                Client.outputStream.writeObject("add_order," + orderNumber + "," + id);
                String response = (String) Client.inputStream.readObject();

                attention.setText(response);
            } catch (Exception e1) {
                e1.printStackTrace();
                attention.setText("Неверные данные!");
            }
        });
        writeToFileButton.addActionListener(e -> {

        });

        attention = new JLabel();
        attention.setSize(740, 30);
        attention.setLocation(30, 260);
        attention.setText("");
        attention.setVisible(true);
        addNumber = new JTextField();
        addNumber.setSize(40, 40);
        addNumber.setLocation(700, 100);
        addNumber.setVisible(true);

        panel.add(attention);
        panel.add(viewButton);
        panel.add(addButton);
        panel.add(addNumber);
        panel.add(writeToFileButton);
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
        ordersScroll = new JScrollPane(ordersTable);
        ordersScroll.setSize(740, 160);
        ordersScroll.setLocation(30, 580);
        ordersScroll.setVisible(true);
        panel.add(ordersScroll);
    }
}
