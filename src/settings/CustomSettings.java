package settings;

public interface CustomSettings {
    String hostname = "jdbc:mysql://localhost:3306/";
    String databaseName = "coursach";
    String username = "phillip";
    String password = "password";

   /*
    CREATE TABLE content (id int PRIMARY KEY AUTO_INCREMENT, name varchar(50) NOT NULL, firm varchar(50) NOT NULL, year_of_publishing int NOT NULL, price int NOT NULL, amount int NOT NULL);
    CREATE TABLE orders(user_id int, content_id int NOT NULL, amount int NOT NULL, FOREIGN KEY (user_id) REFERENCES users(id), FOREIGN KEY (content_id) REFERENCES content(id));


    SELECT user_id, users.login, content_id, content.name, orders.amount FROM orders
                    LEFT JOIN users ON orders.user_id = users.id
                    LEFT JOIN content ON orders.content_id = content.id;
    */
}
