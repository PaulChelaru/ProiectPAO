package Products;

import Suppliers.Supplier;

import java.util.Scanner;

public abstract class Product {
    private String name;
    private int price;
    private String description;
    private Supplier supplier;

    public Product(){}

    public Product(String name, int price, String description, Supplier supplier) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.supplier= supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "name= '" + name + '\'' +
                ", price= " + price +
                ", description= " + description + '\'' +
                ", supplier= " + supplier;
    }
}
