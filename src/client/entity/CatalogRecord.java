package client.entity;

import java.io.Serializable;

public class CatalogRecord  implements Serializable{
    int id;
    String name;
    String firm;
    int yearOfPublishing;
    int price;
    int amount;

    public CatalogRecord(int id, String name, String firm, int yearOfPublishing, int price, int amount) {
        this.id = id;
        this.name = name;
        this.firm = firm;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.amount = amount;
    }
}
