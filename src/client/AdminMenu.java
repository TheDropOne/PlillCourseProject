package client;

import server.entity.CatalogRecord;
import client.entity.InsertRecord;

import javax.swing.*;
import java.util.ArrayList;

public class AdminMenu extends JFrame {
    private JButton viewButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;

    private JPanel panel;
    private JScrollPane scroll;
    private ContentTable contentTable;

    private JTextField[] fields;
    private JTextField deleteNumber;
    private JTextField updateNumber;
    private JLabel attention;


    public AdminMenu(String login) {
        super("Меню администрации, Ваш ник  - " + login);
        panel = new JPanel(null);
        viewButton = new JButton("Просмотр");
        addButton = new JButton("Добавить");
        updateButton = new JButton("Редактировать");
        deleteButton = new JButton("Удалить");
        setSize(800, 800);
        viewButton.setSize(230, 100);
        viewButton.setLocation(130, 60);
        viewButton.addActionListener(e -> {
            try {
                Client.outputStream.writeObject("catalog_records,");
                ArrayList<CatalogRecord> records = (ArrayList<CatalogRecord>) Client.inputStream.readObject();
                attachTable(new ContentTable(records));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        addButton.addActionListener(e -> {
            try {
                InsertRecord record = new InsertRecord();
                record.setName(fields[0].getText());
                record.setFirm(fields[1].getText());
                record.setYearOfPublishing(Integer.parseInt(fields[2].getText()));
                record.setPrice(Integer.parseInt(fields[3].getText()));
                record.setAmount(Integer.parseInt(fields[4].getText()));

                Client.outputStream.writeObject("add_record," + record.toString());
                String response = (String) Client.inputStream.readObject();

                attention.setText(response);

            } catch (Exception e1) {
                e1.printStackTrace();
                attention.setText("Неверные данные!");
            }
        });
        deleteButton.addActionListener(e -> {
            try {
                Client.outputStream.writeObject("delete_record," + Integer.parseInt(deleteNumber.getText()));
                String response = (String) Client.inputStream.readObject();

                attention.setText(response);

            } catch (Exception e1) {
                e1.printStackTrace();
                attention.setText("Неверные данные!");
            }
        });
        updateButton.addActionListener(e -> {
            try {
                InsertRecord record = new InsertRecord();
                record.setName(fields[0].getText());
                record.setFirm(fields[1].getText());
                record.setYearOfPublishing(Integer.parseInt(fields[2].getText()));
                record.setPrice(Integer.parseInt(fields[3].getText()));
                record.setAmount(Integer.parseInt(fields[4].getText()));

                Client.outputStream.writeObject("update_record," + record.toString() + "," + Integer.parseInt(updateNumber.getText()));
                String response = (String) Client.inputStream.readObject();

                attention.setText(response);
            } catch (Exception e1) {
                e1.printStackTrace();
                attention.setText("Неверные данные!");
            }
        });
        addButton.setSize(230, 100);
        addButton.setLocation(430, 60);
        updateButton.setSize(230, 100);
        updateButton.setLocation(130, 200);
        deleteButton.setSize(230, 100);
        deleteButton.setLocation(430, 200);

        fields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            fields[i] = new JTextField();
            fields[i].setSize(120, 30);
            fields[i].setLocation(25 + 125 * (i + 1), 660);
            fields[i].setVisible(true);
            panel.add(fields[i]);
        }
        deleteNumber = new JTextField();
        deleteNumber.setSize(40, 40);
        deleteNumber.setLocation(700, 230);
        deleteNumber.setVisible(true);

        updateNumber = new JTextField();
        updateNumber.setSize(40, 40);
        updateNumber.setLocation(60, 230);
        updateNumber.setVisible(true);

        attention = new JLabel();
        attention.setSize(300, 30);
        attention.setLocation(150, 700);
        attention.setText("");
        attention.setVisible(true);
        add(attention);

        panel.add(viewButton);
        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(deleteNumber);
        panel.add(updateNumber);
        add(panel);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void attachTable(ContentTable contentTable) {
        this.contentTable = contentTable;
        scroll = new JScrollPane(contentTable);
        scroll.setSize(740, 320);
        scroll.setLocation(30, 330);
        scroll.setVisible(true);
        panel.add(scroll);
    }
}
