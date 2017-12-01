package client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by philip on 29.11.17.
 */
public class UserMenu extends JFrame {

    private JButton viewButton;
    private JButton addButton;

    private JList content;

    public UserMenu(String login) throws HeadlessException {
        super("Меню пользователя, Ваш ник  - " + login);
        viewButton = new JButton("Просмотр");
        addButton = new JButton("Добавить");
        content = new JList();
        setSize(800, 800);
        viewButton.setSize(230, 100);
        viewButton.setLocation(130, 60);
        addButton.setSize(230, 100);
        addButton.setLocation(430, 60);
        content.setSize(740, 320);
        content.setLocation(30, 330);
        add(viewButton);
        add(addButton);
        add(content);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
