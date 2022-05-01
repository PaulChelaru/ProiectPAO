package Services;

import Categories.Accessories;
import Categories.Clothing;
import Categories.Mobile;
import GenericCSV.Singleton;
import Products.*;
import Stocks.Stock;
import Suppliers.Supplier;

import java.text.SimpleDateFormat;
import java.util.*;

public class Services {

    public static void display() {
        System.out.println("""
                WELCOME
                1.Add product to a specific stock\s
                2.Delete product from a specific stock\s
                3.Change the price of a product from a specific stock
                4.Display all products from a specific stock
                5.Increase the products' price from a specific stock
                6.Decrease the products' price from a specific stock
                7.Check the price of a product from a specific stock
                8.Add a stock\s
                9.Delete a stock\s
                10.Display the cheapest product from a specific stock
                11.Display all belts\s
                12.Display all jackets\s
                13.Display all jewelry\s
                14.Display all phones\s
                15.Display all products from a specific supplier\s
                0. Exit\s
                """
        );
    }

    public static List<Product> readProducts() {
        return Singleton.getInstance().readProducts("Products.csv");
    }

    public static void addProductsFromCSV(Stock stock, List<Product> products){
        for(Product product : products)
            addProduct(stock, product,1);
    }

    public static void storeCurrentProducts(Set<Stock>stocks) {
        for(Stock stock : stocks) {
            for (Map.Entry<Product, Integer> entry : stock.getMobile().getProducts().entrySet()) {
                writeProductInCSV(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<Product, Integer> entry : stock.getClothing().getProducts().entrySet()) {
                writeProductInCSV(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<Product, Integer> entry : stock.getAccessories().getProducts().entrySet()) {
                writeProductInCSV(entry.getKey(), entry.getValue());
            }
        }
    }

    public static void writeProductInCSV(Product product, Integer number) {
        if (product instanceof Belt) {
            String[] data = {product.getName(), Integer.toString(product.getPrice()), product.getDescription(), product.getSupplier().getName(), ((Belt) product).getSize(), Integer.toString(number)};
            Singleton.getInstance().writeInCsv("Belts.csv", data);
        }
        if (product instanceof Jacket) {
            String[] data = {product.getName(), Integer.toString(product.getPrice()), product.getDescription(), product.getSupplier().getName(), ((Jacket) product).getColor(), ((Jacket) product).getSize(), Integer.toString(number)};
            Singleton.getInstance().writeInCsv("Jackets.csv", data);
        }

        if (product instanceof Phone) {
            String[] data = {product.getName(), Integer.toString(product.getPrice()), product.getDescription(), product.getSupplier().getName(), Integer.toString(((Phone) product).getStorage()), Double.toString(((Phone) product).getFrontCamera()), Double.toString(((Phone) product).getBackCamera()), Integer.toString(number)};
            Singleton.getInstance().writeInCsv("Phones.csv", data);
        }
        if (product instanceof Jewelry) {
            String[] data = {product.getName(), Integer.toString(product.getPrice()), product.getDescription(), product.getSupplier().getName(), ((Jewelry) product).getMaterial(), Double.toString(((Jewelry) product).getWeight()), Integer.toString(number)};
            Singleton.getInstance().writeInCsv("Jewelries.csv", data);
        }
    }

    public static List<Supplier> readSuppliers() {
        return Singleton.getInstance().readSuppliers("ReadSuppliers.csv");
    }

    public static void addSupplierFromCSV(ArrayList<Supplier> suppliers, List<Supplier> supplierList ){
        for(Supplier supplier : supplierList)
            addSupplier(suppliers, supplier);
    }

    public static void storeCurrentSuppliers(ArrayList<Supplier> suppliers) {
        for(Supplier supplier : suppliers) {
            writeSupplierInCSV(supplier);
        }
    }


    public static void writeSupplierInCSV(Supplier supplier) {
        String[] data = {supplier.getName(), supplier.getAddress().toString(), supplier.getPhoneNumber()};
        Singleton.getInstance().writeInCsv("Suppliers.csv", data);
    }

    public static void actionCompleted(String name){
        String [] data = {name, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date())};
        Singleton.getInstance().writeInCsv("Actions.csv", data);
    }

    public static void addProduct(Stock stock, Product product, int numberOfProducts) {
        actionCompleted("addProduct");
        writeProductInCSV(product, numberOfProducts);
        if(product instanceof Jewelry || product instanceof Belt) {
            stock.getAccessories().addProduct(product, numberOfProducts);
        } else if(product instanceof Jacket)
            if (stock.getClothing() == null) {
                Clothing clothing = new Clothing(product, numberOfProducts);
                stock.setClothing(clothing);
            } else {
                stock.getClothing().addProduct(product, numberOfProducts);
            }
        else if(product instanceof Phone)
            if (stock.getMobile() == null) {
                Mobile mobile = new Mobile(product, numberOfProducts);
                stock.setMobile(mobile);
            } else {
                stock.getMobile().addProduct(product, numberOfProducts);
            }
    }

    public static void addSupplier(ArrayList<Supplier> suppliers, Supplier supplier) {
        actionCompleted("addSupplier");
        writeSupplierInCSV(supplier);
        suppliers.add(supplier);
    }

    public static void displayStocks(Set<Stock> stocks){
        actionCompleted("displayStocks");
        for(Stock stock : stocks) {
            System.out.println("\n" + stock.getName());
            for (Map.Entry<Product, Integer> entry : stock.getMobile().getProducts().entrySet())
                System.out.println(entry.getKey());
            for (Map.Entry<Product, Integer> entry : stock.getAccessories().getProducts().entrySet())
                System.out.println(entry.getKey());
            for (Map.Entry<Product, Integer> entry : stock.getClothing().getProducts().entrySet())
                System.out.println(entry.getKey());
        }
    }

    public static void displayStock(Set<Stock> stocks, String stockName){
        actionCompleted("displayStock");
        for(Stock stock : stocks) {
            if(stock.getName().equals(stockName)) {
                for (Map.Entry<Product, Integer> product : stock.getMobile().getProducts().entrySet())
                    System.out.println(product.getKey());
                for (Map.Entry<Product, Integer> product : stock.getAccessories().getProducts().entrySet())
                    System.out.println(product.getKey());
                for (Map.Entry<Product, Integer> product : stock.getClothing().getProducts().entrySet())
                    System.out.println(product.getKey());
            }
        }
    }

    public static void removeProduct(Set<Stock> stocks, String stockName, String productName) {
        actionCompleted("removeProduct");
        for(Stock stock : stocks)
            if(stock.getName().equals(stockName)) {
                for(Product product : stock.getClothing().getProducts().keySet())
                    if (product.getName().equals(productName)) {
                        stock.getClothing().getProducts().remove(product);
                        break;
                    }
                for(Product product : stock.getMobile().getProducts().keySet())
                    if (product.getName().equals(productName)) {
                        stock.getMobile().getProducts().remove(product);
                        break;
                    }
                for(Product product : stock.getAccessories().getProducts().keySet())
                    if (product.getName().equals(productName)) {
                        stock.getAccessories().getProducts().remove(product);
                        break;
                    }
            }
    }

    public static void increasePrice(Set<Stock> stocks, String stockName, int percentage) {
        actionCompleted("increasePrice");
        for(Stock stock : stocks)
            if(stock.getName().equals(stockName)) {
                stock.getMobile().increasePrice(percentage);
                stock.getAccessories().increasePrice(percentage);
                stock.getClothing().increasePrice(percentage);
            }
    }

    public static void decreasePrice(Set<Stock> stocks, String stockName, int percentage) {
        actionCompleted("decreasePrice");
        for(Stock stock : stocks)
            if(stock.getName().equals(stockName)) {
                stock.getMobile().decreasePrice(percentage);
                stock.getAccessories().decreasePrice(percentage);
                stock.getClothing().decreasePrice(percentage);
            }
    }

    public static void cheapestProduct(Set<Stock> stocks, String stockName) {
        actionCompleted("cheapestProduct");
        for(Stock stock : stocks)
            if(stock.getName().equals(stockName)) {
                if (stock.getClothing().cheapestProduct().getPrice() < stock.getAccessories().cheapestProduct().getPrice() &&
                        stock.getClothing().cheapestProduct().getPrice() < stock.getMobile().cheapestProduct().getPrice()) {
                    System.out.println(stock.getClothing().cheapestProduct());
                }
                if (stock.getMobile().cheapestProduct().getPrice() < stock.getAccessories().cheapestProduct().getPrice() &&
                        stock.getMobile().cheapestProduct().getPrice() < stock.getClothing().cheapestProduct().getPrice()) {
                    System.out.println(stock.getMobile().cheapestProduct());
                }
                if (stock.getAccessories().cheapestProduct().getPrice() < stock.getClothing().cheapestProduct().getPrice() &&
                        stock.getAccessories().cheapestProduct().getPrice() < stock.getMobile().cheapestProduct().getPrice()) {
                    System.out.println(stock.getAccessories().cheapestProduct());
                }
            }
    }

    public static void addStock(Set<Stock> stocks) {
        actionCompleted("addStock");

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the stock: ");
        String stockName = in.next();
        Stock stock = new Stock(stockName);

        System.out.println("The number of belts: ");
        int numberBelts = in.nextInt();
        for(int i = 0; i <= numberBelts; i ++) {
            Belt belt = Belt.addBelt();
            addProduct(stock, belt, numberBelts);
        }

        System.out.println("The number of jackets: ");
        int numberJackets = in.nextInt();
        for(int i = 0; i <= numberJackets; i ++) {
            Jacket jacket = Jacket.addJacket();
            addProduct(stock, jacket, numberJackets);
        }

        System.out.println("The number of jewelries: ");
        int numberJewelries = in.nextInt();
        for(int i = 0; i <= numberJewelries; i ++) {
            Jewelry jewelry = Jewelry.addJewelry();
            addProduct(stock, jewelry, numberJewelries);
        }

        System.out.println("The number of phones: ");
        int numberPhones = in.nextInt();
        for(int i = 0; i <= numberPhones; i ++) {
            Phone phone = Phone.addPhone();
            addProduct(stock, phone, numberPhones);
        }
        stocks.add(stock);
    }

    public static void deleteStock(Set<Stock> stocks, String stockName) {
        actionCompleted("deleteStock");
        stocks.removeIf(stock -> stock.getName().equals(stockName));
    }

    public static void stockSupplierProducts(Set<Stock> stocks, String supplierName) {
        actionCompleted("stockSupplierProducts");

        for(Stock stock : stocks) {
            for (Map.Entry<Product, Integer> product : stock.getMobile().getProducts().entrySet())
                if(product.getKey().getSupplier().getName().equals(supplierName))
                    System.out.println(product.getKey());
            for (Map.Entry<Product, Integer> product : stock.getClothing().getProducts().entrySet())
                if(product.getKey().getSupplier().getName().equals(supplierName))
                    System.out.println(product.getKey());
            for (Map.Entry<Product, Integer> product : stock.getAccessories().getProducts().entrySet())
                if(product.getKey().getSupplier().getName().equals(supplierName))
                    System.out.println(product.getKey());
        }
    }

    public static void printBelts(Set<Stock> stocks) {
        actionCompleted("printBelts");

        for(Stock stock : stocks) {
            for (Map.Entry<Product, Integer> product : stock.getAccessories().getProducts().entrySet())
                if(product.getKey() instanceof Belt)
                    System.out.println(product.getKey());
        }
    }

    public static void printJewelries(Set<Stock> stocks) {
        actionCompleted("printJewelries");

        for(Stock stock : stocks) {
            for (Map.Entry<Product, Integer> product : stock.getAccessories().getProducts().entrySet())
                if(product.getKey() instanceof Jewelry)
                    System.out.println(product.getKey());
        }
    }

    public static void printJackets(Set<Stock> stocks) {
        actionCompleted("printJackets");

        for(Stock stock : stocks) {
            for (Map.Entry<Product, Integer> product : stock.getClothing().getProducts().entrySet())
                if(product.getKey() instanceof Jacket)
                    System.out.println(product.getKey());
        }
    }

    public static void printPhones(Set<Stock> stocks) {
        actionCompleted("printPhones");

        for(Stock stock : stocks) {
            for (Map.Entry<Product, Integer> product : stock.getMobile().getProducts().entrySet())
                if(product.getKey() instanceof Phone)
                    System.out.println(product.getKey());
        }
    }

    public static void modifyPrice(Set<Stock> stocks, String stockName, String productName, int newPrice) {
        actionCompleted("modifyPrice");

        for(Stock stock :stocks)
            if(stock.getName().equals(stockName)) {
                for(Product product : stock.getClothing().getProducts().keySet())
                    if (product.getName().equals(productName)) {
                        product.setPrice(newPrice);
                        break;
                    }
                for(Product product : stock.getMobile().getProducts().keySet())
                    if (product.getName().equals(productName)) {
                        product.setPrice(newPrice);
                        break;
                    }
                for(Product product : stock.getAccessories().getProducts().keySet())
                    if (product.getName().equals(productName)) {
                        product.setPrice(newPrice);
                        break;
                    }
            }
    }

    public static void checkPrice(Set<Stock> stocks, String stockName, String productName) {
        actionCompleted("checkPrice");

        for (Stock stock : stocks)
            if (stock.getName().equals(stockName)) {
                if (stock.getAccessories().searchProduct(productName)) {
                    for (Map.Entry<Product, Integer> product : stock.getAccessories().getProducts().entrySet())
                        if (product.getKey().getName().equals(productName))
                            System.out.println(product.getKey().getPrice());
                }
                if (stock.getMobile().searchProduct(productName)) {
                    for (Map.Entry<Product, Integer> product : stock.getMobile().getProducts().entrySet())
                        if (product.getKey().getName().equals(productName))
                            System.out.println(product.getKey().getPrice());
                }
                if (stock.getClothing().searchProduct(productName)) {
                    for (Map.Entry<Product, Integer> product : stock.getClothing().getProducts().entrySet())
                        if (product.getKey().getName().equals(productName))
                            System.out.println(product.getKey().getPrice());
                }
            }
    }
}
