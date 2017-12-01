package client.entity;

import java.io.Serializable;

public class InsertRecord {
    String name;
    String firm;
    int yearOfPublishing;
    int price;
    int amount;

    public InsertRecord() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return name + " " + firm + " " + yearOfPublishing + " " + price + " " + amount;
    }
}
