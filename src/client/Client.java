package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * Created by philip on 29.11.17.
 */
public class Client extends JFrame {
    private JPanel panel;
    private JButton authorizationButton;
    static String clientMessage;
    static Socket socket;
    static ObjectOutputStream outstream; //поток вывода
    static ObjectInputStream inputstream; //поток ввода

    public Client() {
        initializationComponents();
        clientMessage = "";
        try {
            socket = new Socket(InetAddress.getLocalHost(), 8055);

            outstream = new ObjectOutputStream(socket.getOutputStream());
            inputstream = new ObjectInputStream(socket.getInputStream());

        } catch (UnknownHostException ex) {
            System.err.println("Адрес недоступен" + ex);
        } catch (IOException ex) {
            System.err.println("Произошла потоковая ошибка" + ex);
        }
    }

    private void initializationComponents() {
        panel = new JPanel();
        authorizationButton = new JButton("Перейти к авторизации");
        setTitle("Окно входа");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        authorizationButton.setSize(100, 50);
        authorizationButton.setLocation(50, 25);
        add(authorizationButton);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                formWindowClosing(ev);
            }
        });
        authorizationButton.addActionListener(evt -> userLoginButtonActionPerformed(evt));
        GroupLayout jPanel1Layout = new GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(87, 87, 87)
                                                .addComponent(authorizationButton, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(55, 55, 55)))
                                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGap(45, 45, 45)
                                .addComponent(authorizationButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(108, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        pack();
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
    }

    private static void disconnectConnection() {
        try {
            if (inputstream != null) {
                inputstream.close();
            }
            if (outstream != null) {
                outstream.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void userLoginButtonActionPerformed(ActionEvent evt) {
        invokeLater(() -> {
            try {
                new Authorization().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        this.setVisible(false);
    }

    private void formWindowClosing(WindowEvent ev) {
        disconnectConnection();
    }

    public static void main(String[] args) {
        invokeLater(() -> new Client().setVisible(true));
    }
}

