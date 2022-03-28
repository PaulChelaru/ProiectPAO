package Products;

import Suppliers.Supplier;

import java.util.Scanner;

public class Belt extends Product {
    private String size;

    public Belt(String name, int price, String description, Supplier supplier, String size) {
        super(name, price, description, supplier);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public static Belt addBelt() {
        Scanner in = new Scanner(System.in);
        System.out.println("Porudct name: ");
        String productName = in.next();
        System.out.println("Size: ");
        String size = in.next();
        System.out.println("Price: ");
        int price = in.nextInt();
        System.out.println("Description: ");
        String description = in.next();
        Supplier supplier = Supplier.addSupplier();

        return new Belt(productName, price, description, supplier, size);
    }

    @Override
    public String toString() {
        return "Belt{" +
                " size='" + size + '\'' +
                super.toString() + '}';
    }
}
