import Address.Address;
import Categories.Accessories;
import Categories.Clothing;
import Categories.Mobile;
import GenericCSV.Singleton;
import Products.*;
import Services.Services;
import Stocks.Stock;
import Suppliers.Supplier;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Address address1 = new Address("Strada1", "Cluj", "Romania", "203234");
        Address address2 = new Address("Strada2", "Buchares", "Romania", "2342351");
        Supplier supplier1 = new Supplier("Shein", address1, "0700000000");
        Supplier supplier2 = new Supplier("Emag", address2, "0700444320");
        ArrayList<Supplier> suppliers = new ArrayList<>();
        suppliers.add(supplier1);
        suppliers.add(supplier2);

        Phone phone = new Phone("iPhone", 3000, "Telefon marca Apple", supplier2, 256, 10, 10);
        Belt belt = new Belt("Pattern belt", 1000, "Gucci belt with logo pattern", supplier1, "S");
        Jacket jacket = new Jacket("Leather jacket", 600, "Zara leather jacket", supplier1, "black", "L");
        Jewelry jewelry = new Jewelry("Gold bracelet", 4000, "Tailor gold bracelet", supplier2, "gold", 123);

        Map<Product, Integer> accessories = new HashMap<>();
        Map<Product, Integer> clothing = new HashMap<>();
        Map<Product, Integer> mobiles = new HashMap<>();
        mobiles.put(phone, 3);
        accessories.put(jewelry, 6);
        accessories.put(belt, 2);
        clothing.put(jacket, 15);

        Accessories accessoriesCategory = new Accessories(accessories);
        Clothing clothingCategory = new Clothing(clothing);
        Mobile mobileCategory = new Mobile(mobiles);

        Stock stock1 = new Stock("Stock1", mobileCategory);
        Stock stock2 = new Stock("Stock2", clothingCategory, accessoriesCategory);

        Set<Stock> stocks = new TreeSet<>();
        stocks.add(stock1);
        stocks.add(stock2);

        Singleton.getInstance().createNewFile("Phones.csv");
        Singleton.getInstance().createNewFile("Belts.csv");
        Singleton.getInstance().createNewFile("Jackets.csv");
        Singleton.getInstance().createNewFile("Jewelries.csv");
        Singleton.getInstance().createNewFile("Actions.csv");
        Singleton.getInstance().createNewFile("Suppliers.csv");

        Services.storeCurrentProducts(stocks);
        Services.addProductsFromCSV(stock1, Services.readProducts());
        Services.storeCurrentSuppliers(suppliers);
        Services.addSupplierFromCSV(suppliers, Services.readSuppliers());

        int action = -1;
        while (action != 0) {
            Services.display();
            System.out.println("Enter the number of the action: " );
            action = in.nextInt();

            if (action == 1) {
                System.out.println("You chose to add a product to a specific stock.");

                System.out.println("What's the stock name?");
                String stockName = in.next();

                System.out.println("How many products do you want to add?");
                int numberOfProducts = in.nextInt();

                System.out.println("What kind of product will be added? (belt / jacket / phone / jewelry");
                String productKind = in.next();

                if (productKind.equals("belt")) {
                    Belt belt1 = Belt.addBelt();
                    for (Stock stock : stocks)
                        if (stock.getName().equals(stockName)) {
                            Services.addProduct(stock, belt1, numberOfProducts);
                            Services.displayStock(stocks, stockName);
                        }
                }
                if (productKind.equals("jacket")) {
                    Jacket jacket1 = Jacket.addJacket();
                    for (Stock stock : stocks)
                        if (stock.getName().equals(stockName)) {
                            Services.addProduct(stock, jacket1, numberOfProducts);
                            Services.displayStock(stocks, stockName);
                        }
                }

                if (productKind.equals("phone")) {
                    Phone phone1 = Phone.addPhone();
                    for (Stock stock : stocks)
                        if (stock.getName().equals(stockName)) {
                            Services.addProduct(stock, phone1, numberOfProducts);
                            Services.displayStock(stocks, stockName);
                        }
                }

                if (productKind.equals("jewelry")) {
                    Jewelry jewelry1 = Jewelry.addJewelry();
                    for (Stock stock : stocks)
                        if (stock.getName().equals(stockName)) {
                            Services.addProduct(stock, jewelry1, numberOfProducts);
                            Services.displayStock(stocks, stockName);
                        }
                }
            }

            if(action == 2) {
                System.out.println("What's the stock name?");
                String stockName = in.next();

                System.out.println("What's the product name?");
                String productName = in.next();

                System.out.println("Initial stock: \n");
                Services.displayStock(stocks, stockName);
                Services.removeProduct(stocks, stockName, productName);
                System.out.println("Stock after deleting the product: \n");
                Services.displayStock(stocks, stockName);
            }

            if(action == 3) {
                System.out.println("What's the stock name?");
                String stockName = in.next();

                System.out.println("What's the product name?");
                String productName = in.next();

                Services.checkPrice(stocks, stockName, productName);
                System.out.println("Insert the new price");
                int newPrice = in.nextInt();
                Services.modifyPrice(stocks,stockName,productName,newPrice);
                Services.checkPrice(stocks, stockName, productName);
            }

            if(action == 4) {
                System.out.println("What's the stock name?");
                String stockName = in.next();
                Services.displayStock(stocks, stockName);
            }

            if(action == 5) {
                System.out.println("What's the stock name?");
                String stockName = in.next();
                System.out.println("The stock before update: ");
                Services.displayStock(stocks, stockName);
                System.out.println("How much % do you want to increase the price of the products?");
                int percentage = in.nextInt();
                Services.increasePrice(stocks, stockName, percentage);
                System.out.println("The stock after update: ");
                Services.displayStock(stocks, stockName);
            }

            if(action == 6) {
                System.out.println("What's the stock name?");
                String stockName = in.next();
                System.out.println("The stock before update: ");
                Services.displayStock(stocks, stockName);
                System.out.println("How much % do you want to decrease the price of the products?");
                int percentage = in.nextInt();
                Services.decreasePrice(stocks, stockName, percentage);
                System.out.println("The stock after update: ");
                Services.displayStock(stocks, stockName);
            }

            if(action ==7) {
                System.out.println("What's the stock name?");
                String stockName = in.next();

                System.out.println("What's the product name?");
                String productName = in.next();
                Services.checkPrice(stocks, stockName, productName);
            }


            if(action == 8) {
                Services.addStock(stocks);
                System.out.println("The list of all the stocks: ");
                Services.displayStocks(stocks);
            }

            if(action == 9) {
                Services.displayStocks(stocks);
                System.out.println("What's the stock name you weant to delete?");
                String stockName = in.next();

                Services.deleteStock(stocks, stockName);

                System.out.println("The list of all the stocks: ");
                Services.displayStocks(stocks);
            }

            if(action == 10) {
                System.out.println("What's the name of the stock?");
                String stockName = in.next();

                System.out.println("The cheapest item in this stock: ");
                Services.cheapestProduct(stocks, stockName);
            }

            if(action == 11) {
                Services.printBelts(stocks);
            }

            if(action == 12) {
                Services.printJackets(stocks);
            }

            if(action == 13) {
                Services.printJewelries(stocks);
            }

            if(action == 14) {
                Services.printPhones(stocks);
            }

            if(action == 15) {
                System.out.println("Supplier name: ");
                String supplierName = in.next();
                Services.stockSupplierProducts(stocks, supplierName);
            }

            if(action == 0) {
                System.out.println("The application will stop.");
            }
        }
    }


}
