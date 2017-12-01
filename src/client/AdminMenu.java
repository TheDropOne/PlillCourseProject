package client;

import client.entity.CatalogRecord;
import server.CatalogTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class AdminMenu extends JFrame {
    private JButton viewButton;
    private JButton addButton;
    private JButton redactButton;
    private JButton deleteButton;

    private JScrollPane scroll;
    private ContentTable contentTable;


    public AdminMenu(String login) {
        super("Меню администрации, Ваш ник  - " + login);
        viewButton = new JButton("Просмотр");
        addButton = new JButton("Добавить");
        redactButton = new JButton("Редактировать");
        deleteButton = new JButton("Удалить");
        setSize(800, 800);
        viewButton.setSize(230, 100);
        viewButton.setLocation(130, 60);
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client.outputStream.writeObject("catalog_records,");
                    ArrayList<CatalogRecord> records = (ArrayList<CatalogRecord>) Client.inputStream.readObject();
                    attachTable(new ContentTable(records));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        addButton.setSize(230, 100);
        addButton.setLocation(430, 60);
        redactButton.setSize(230, 100);
        redactButton.setLocation(130, 200);
        deleteButton.setSize(230, 100);
        deleteButton.setLocation(430, 200);
        add(viewButton);
        add(addButton);
        add(redactButton);
        add(deleteButton);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void attachTable(ContentTable contentTable) {
        this.contentTable = contentTable;
        scroll = new JScrollPane(contentTable);
        scroll.setSize(740, 320);
        scroll.setLocation(30, 330);
        scroll.setVisible(true);
        add(scroll);
    }
}
