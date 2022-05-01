package Products;

import Suppliers.Supplier;

import java.util.Scanner;

public class Jacket extends Product{
    private String color;
    private String size;

    public Jacket(String name, int price, String description, Supplier supplier, String color, String size) {
        super(name, price, description, supplier);
        this.color = color;
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public static Jacket addJacket() {
        Scanner in = new Scanner(System.in);
        System.out.println("Porudct name: ");
        String productName = in.next();
        System.out.println("Size: ");
        String size = in.next();
        System.out.println("Color: ");
        String color = in.next();
        System.out.println("Price: ");
        int price = in.nextInt();
        System.out.println("Description: ");
        String description = in.next();
        Supplier supplier = Supplier.addSupplier();

        return new Jacket(productName, price, description, supplier, color, size);
    }

    @Override
    public String toString() {
        return "Jacket{" +
                "color='" + color + '\'' +
                ", size='" + size + '\'' +
                super.toString() + '}';
    }
}
