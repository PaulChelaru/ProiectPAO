package Products;

import Suppliers.Supplier;

import java.util.Scanner;

public class Jewelry extends Product {
    private String material;
    private double weight;

    public Jewelry(String name, int price, String description, Supplier supplier, String material, double weight) {
        super(name, price, description, supplier);
        this.material = material;
        this.weight = weight;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static Jewelry addJewelry() {
        Scanner in = new Scanner(System.in);
        System.out.println("Porudct name: ");
        String productName = in.next();

        System.out.println("Size: ");
        String size = in.next();

        System.out.println("Material: ");
        String material = in.next();

        System.out.println("Weight: ");
        double weight = in.nextDouble();

        System.out.println("Color: ");
        String color = in.next();

        System.out.println("Price: ");
        int price = in.nextInt();

        System.out.println("Description: ");
        String description = in.next();

        Supplier supplier = Supplier.addSupplier();

        return new Jewelry(productName, price, description, supplier, material, weight);
    }

    @Override
    public String toString() {
        return "Jewelry{" +
                "material='" + material + '\'' +
                ", weight=" + weight +
                super.toString() + '}';
    }
}
