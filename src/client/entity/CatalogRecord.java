package client.entity;

import java.io.Serializable;

public class CatalogRecord implements Serializable {
    int id;
    String name;
    String firm;
    int yearOfPublishing;
    int price;
    int amount;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirm() {
        return firm;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public CatalogRecord(int id, String name, String firm, int yearOfPublishing, int price, int amount) {
        this.id = id;
        this.name = name;
        this.firm = firm;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.amount = amount;
    }
}
