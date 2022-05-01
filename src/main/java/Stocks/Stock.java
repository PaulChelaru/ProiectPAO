package Stocks;

import Categories.Accessories;
import Categories.Clothing;
import Categories.Mobile;
import Products.*;

public class Stock implements Comparable<Stock> {
    private String name;
    private Accessories accessories;
    private Clothing clothing;
    private Mobile mobile;

    public Stock(String name) {
        this.name = name;
        this.accessories = new Accessories();
        this.clothing = new Clothing();
        this.mobile = new Mobile();
    }

    public Stock(String name, Product product, int quantity){
        this.name = name;
        if(product instanceof Jacket) {
            this.clothing = new Clothing(product, quantity);
            this.accessories = new Accessories();
            this.mobile = new Mobile();
        }

        if(product instanceof Jewelry || product instanceof Belt) {
            this.clothing = new Clothing();
            this.accessories = new Accessories(product, quantity);
            this.mobile = new Mobile();
        }

        if(product instanceof Phone) {
            this.clothing = new Clothing();
            this.accessories = new Accessories();
            this.mobile = new Mobile(product, quantity);
        }
    }

    public Stock(String name, Accessories accessories, Clothing clothing, Mobile mobile) {
        this.name = name;
        this.accessories = accessories;
        this.clothing = clothing;
        this.mobile = mobile;
    }

    public Stock(String name, Mobile mobile) {
        this.name = name;
        this.accessories = new Accessories();
        this.clothing = new Clothing();
        this.mobile = mobile;
    }

    public Stock(String name, Clothing clothing) {
        this.name = name;
        this.accessories = new Accessories();
        this.clothing = clothing;
        this.mobile = new Mobile();
    }

    public Stock(String name, Accessories accessories) {
        this.name = name;
        this.accessories = accessories;
        this.clothing = new Clothing();
        this.mobile = new Mobile();
    }

    public Stock(String name, Clothing clothing, Accessories accessories) {
        this.name = name;
        this.accessories = accessories;
        this.clothing = clothing;
        this.mobile = new Mobile();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }

    public Clothing getClothing() {
        return clothing;
    }

    public void setClothing(Clothing clothing) {
        this.clothing = clothing;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    @Override
    public int compareTo(Stock o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", accessories=" + accessories +
                ", clothing=" + clothing +
                ", mobile=" + mobile +
                '}';
    }
}
