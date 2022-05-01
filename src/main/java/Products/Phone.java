package Products;

import Suppliers.Supplier;

import java.util.Scanner;

public class Phone extends Product {
    private int storage;
    private double frontCamera;
    private double backCamera;

    public Phone(String name, int price, String description, Supplier supplier, int storage, double frontCamera, double backCamera) {
        super(name, price, description, supplier);
        this.storage = storage;
        this.frontCamera = frontCamera;
        this.backCamera = backCamera;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public double getFrontCamera() {
        return frontCamera;
    }

    public void setFrontCamera(double frontCamera) {
        this.frontCamera = frontCamera;
    }

    public double getBackCamera() {
        return backCamera;
    }

    public void setBackCamera(double backCamera) {
        this.backCamera = backCamera;
    }

    public static Phone addPhone() {
        Scanner in = new Scanner(System.in);
        System.out.println("Porudct name: ");
        String productName = in.next();
        System.out.println("Storage: ");
        int storage = in.nextInt();
        System.out.println("Front camera megapixels:: ");
        double frontCamera = in.nextDouble();
        System.out.println("Back camera megapixels:: ");
        double backCamera = in.nextDouble();
        System.out.println("Price: ");
        int price = in.nextInt();
        System.out.println("Description: ");
        String description = in.next();
        Supplier supplier = Supplier.addSupplier();

        return new Phone(productName, price, description, supplier, storage, frontCamera, backCamera);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "storage=" + storage +
                ", frontCamera=" + frontCamera +
                ", backCamera=" + backCamera +
                super.toString() + '}';
    }

}
