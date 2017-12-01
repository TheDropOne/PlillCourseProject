package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class Authorization extends JFrame {
    private JButton entrance;
    private JButton registration;
    private JTextField loginField;
    private JPasswordField passwordField;

    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel informationLabel;


    Authorization() {
        super("Авторизация");
        entrance = new JButton("Вход");
        registration = new JButton("Регистрация");
        loginLabel = new JLabel("Логин");
        passwordLabel = new JLabel("Пароль");
        loginField = new HintTextField("Введите логин");
        passwordField = new JPasswordField();
        informationLabel = new JLabel();
        setSize(500, 600);
        entrance.setSize(200, 60);
        entrance.setLocation(150, 200);
        registration.setSize(160, 40);
        registration.setLocation(170, 280);
        loginField.setSize(230, 30);
        loginField.setLocation(150, 30);
        passwordField.setSize(230, 30);
        passwordField.setLocation(150, 110);

        loginLabel.setSize(100, 20);
        loginLabel.setLocation(80, 37);
        passwordLabel.setSize(100, 20);
        passwordLabel.setLocation(80, 115);
        informationLabel.setSize(340, 30);
        informationLabel.setLocation(120, 160);
        informationLabel.setText(setColoredText("Ошибка во входных данных!"));
        informationLabel.setForeground(Color.red);
        informationLabel.setFont(new Font("arial", Font.BOLD, 16));
        informationLabel.setOpaque(true);
        informationLabel.setVisible(false);
        add(entrance);
        add(registration);
        add(loginField);
        add(passwordField);
        add(loginLabel);
        add(passwordLabel);
        add(informationLabel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        entrance.addActionListener(e -> checkEntranceButton(e));
    }

    public String setColoredText(String message) {
        return "<html><font color='red'>" + message + "</font></html>";
    }

    private void checkEntranceButton(ActionEvent ev) {
        try {
            String clientMessage;
            String login;
            String password;
            login = loginField.getText();
            password = new String(passwordField.getPassword());
            if (login.isEmpty()) {
                loginLabel.setForeground(Color.red);
                loginLabel.setText("Неверный логин!");
            }
            if (password.isEmpty()) {
                passwordLabel.setForeground(Color.red);
                passwordLabel.setText("Неверный пароль!");
            }

            clientMessage = "CheckUserLogin" + "," + login + "," + password;

            Client.outputStream.writeObject(clientMessage);
            String message = (String) Client.inputStream.readObject();
            String arrayOfMessages[] = message.split(",");
            switch (arrayOfMessages[0]) {
                case "success":
                    if (arrayOfMessages[1].equals(String.valueOf(1))) {
                        new AdminMenu(login).setVisible(true);
                        this.setVisible(false);
                    }
                    if (arrayOfMessages[1].equals(String.valueOf(2))) {
                        new UserMenu(login).setVisible(true);
                        this.setVisible(false);
                    }
                    break;
                case "fail":
                    informationLabel.setText("Ошибка во входных данных!");
                    informationLabel.setVisible(true);
                    break;
                default:
                    informationLabel.setText("Непредвиденная ошибка!");
                    informationLabel.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
