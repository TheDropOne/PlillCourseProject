package client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by philip on 29.11.17.
 */
public class AdminMenu extends JFrame {
    private JButton viewButton;
    private JButton addButton;
    private JButton redactButton;
    private JButton deleteButton;
    private JList content;

    public AdminMenu(String login) {
        super("Меню администрации, Ваш ник  - " + login);
        viewButton = new JButton("Просмотр");
        addButton = new JButton("Добавить");
        redactButton = new JButton("Редактировать");
        deleteButton = new JButton("Удалить");
        content = new JList();
        setSize(800, 800);
        viewButton.setSize(230, 100);
        viewButton.setLocation(130, 60);
        addButton.setSize(230, 100);
        addButton.setLocation(430, 60);
        redactButton.setSize(230, 100);
        redactButton.setLocation(130, 200);
        deleteButton.setSize(230, 100);
        deleteButton.setLocation(430, 200);
        content.setSize(740, 320);
        content.setLocation(30, 330);
        add(viewButton);
        add(addButton);
        add(redactButton);
        add(deleteButton);
        add(content);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
