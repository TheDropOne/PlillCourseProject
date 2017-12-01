package server;

import client.entity.CatalogRecord;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private InetAddress address;
    private ObjectOutputStream outstream;
    private ObjectInputStream inputstream;
    private String clientMessage;
    private DatabaseConnection mydbc;
    private Statement statement;
    private int number;
    private UserTable userTable;
    private CatalogTable catalogTable;

    public ServerThread(Socket s, int number) throws IOException {
        this.number = number;

        outstream = new ObjectOutputStream(s.getOutputStream());//поток вывода
        inputstream = new ObjectInputStream(s.getInputStream());//поток ввода

        address = s.getInetAddress();
        mydbc = new DatabaseConnection();
        mydbc.initialization();
        Connection conn = mydbc.getMyConnection();
        try {
            statement = conn.createStatement();
            userTable = new UserTable(statement, mydbc);
            //pupilTable = new PupilTable(stmt, mdbc);
            catalogTable = new CatalogTable(statement, mydbc);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
    }

    public <T extends Serializable> void writeObj(T str) {
        try {
            outstream.writeObject(str);
        } catch (IOException ex) {
            System.err.println("Произошла потоковая ошибка" + ex);
        }
    }

    public void disconnect() {
        try {
            if (outstream != null) {
                outstream.close();
            }
            if (inputstream != null) {
                inputstream.close();
            }
            System.out.println(address.getHostName() + "Завершено соединение " + number + "-го пользователя");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.interrupt();
        }
    }

    public void run() {
        int i = 0;
        String messageToClient = "";
        String bufMessage;
        String ThreadStop = "";
        try {
            System.out.println("Сервер ожидает действий от клиента");
            while (!ThreadStop.equals("Exit")) {

                bufMessage = (String) inputstream.readObject();
                String messageParts[] = bufMessage.split(",");
                switch (messageParts[0]) {
                    case "CheckUserLogin":
                        String UserLogin = messageParts[1];
                        String UserPassword = messageParts[2];
                        messageToClient = userTable.checkLogin(UserLogin, UserPassword);
                        writeObj(messageToClient);
                        break;
                    case "catalog_records":
                        writeObj((ArrayList<CatalogRecord>) catalogTable.readAllRecords());
                        break;
//                    case "addInPupilsTable":
//                        messageToClient = pupilTable.addInTable(messageParts);
//                        writeObj(messageToClient);
//                        break;
//                    case "deleteFromPupilsTable":
//                        String ID = messageParts[1];
//                        messageToClient = pupilTable.deleteFromTable(ID);
//                        writeObj(messageToClient);
//                        break;
//                    case "editInPupilsTable":
//                        messageToClient = pupilTable.EditInTable(messageParts);
//                        writeObj(messageToClient);
//                        break;
//                    case "filterInPupilTable":
//                        String FilterColumn = messageParts[1];
//                        String FilterValue = messageParts[2];
//                        messageToClient = pupilTable.filterInTable(FilterColumn, FilterValue);
//                        writeObj(messageToClient);
//                        break;
//                    case "sortInPupilTable":
//                        String sortColumn = messageParts[1];
//                        String ifDesc = messageParts[2];
//                        messageToClient = pupilTable.SortInTable(sortColumn, ifDesc);
//                        writeObj(messageToClient);
//                        break;
//                    case "addInOrdersTable":
//                        messageToClient = orderTable.addInTable(messageParts);
//                        writeObj(messageToClient);
//                        break;
//                    case "showUserOrders":
//                        String loginValue = messageParts[1];
//                        messageToClient = orderTable.filterInTable("", loginValue);
//                        writeObj(messageToClient);
//                        break;
//                    case "initTableOrders":
//                        messageToClient = orderTable.readAllRecord();
//                        writeObj(messageToClient);
//                        break;
//                    case "deleteFromOrdersTable":
//                        String id = messageParts[1];
//                        messageToClient = orderTable.deleteFromTable(id);
//                        writeObj(messageToClient);
//                        break;
//                    case "Exit":
//                        ThreadStop = "Exit";
//                        break;
                    default:
                        System.err.println("Неизвестная команда");
                }
            }
        } catch (Exception e) {
            System.err.println("Соединение закрыто");
        } finally {
            disconnect(); // уничтожение потока
        }
    }
}

