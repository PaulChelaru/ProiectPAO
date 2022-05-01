package GenericCSV;

import Address.Address;
import Products.*;
import Suppliers.Supplier;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static Singleton instance;

    private Singleton(){ }

    public static Singleton getInstance() {
        if(instance == null) {
            synchronized (Singleton.class) {
                //createInstance();
                createInstanceIfNull();
            }
        }
        return instance;
    }

    private static void createInstance(){
        instance = new Singleton();
    }
    private static void createInstanceIfNull() {
        if(instance == null) {
            instance = new Singleton();
        }
    }

    public List<Product> readProducts(String file) {
        List<Product> products = new ArrayList<>();

        try {
            // Create an object of file reader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(0)
                    .build();
            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData){
                if(row[0].equals("Belt")) {
                    Belt belt = new Belt(row[1], Integer.parseInt(row[2]), row[3], new Supplier(row[4], new Address(row[5], row[6], row[7], row[8]), row[9]), row[10]);
                    products.add(belt);
                }
                if(row[0].equals("Jacket")) {
                    Jacket jacket = new Jacket(row[1], Integer.parseInt(row[2]), row[3], new Supplier(row[4], new Address(row[5], row[6], row[7], row[8]), row[9]), row[10], row[11]);
                    products.add(jacket);
                }
                if(row[0].equals("Phone")) {
                    Phone phone = new Phone(row[1], Integer.parseInt(row[2]), row[3], new Supplier(row[4], new Address(row[5], row[6], row[7], row[8]), row[9]), Integer.parseInt(row[10]), Double.parseDouble(row[11]), Double.parseDouble(row[12]));
                    products.add(phone);
                }
                if(row[0].equals("Jewelry")) {
                    Jewelry jewelry = new Jewelry(row[1], Integer.parseInt(row[2]), row[3], new Supplier(row[4], new Address(row[5], row[6], row[7], row[8]), row[9]), row[10], Double.parseDouble(row[11]));
                    products.add(jewelry);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Supplier> readSuppliers(String file) {
        List<Supplier> suppliers = new ArrayList<>();

        try {
            // Create an object of file reader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(0)
                    .build();
            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData)
                if (row[0].equals("Supplier")) {
                    Supplier distributor = new Supplier(row[1], new Address(row[2], row[3], row[4], row[5]), row[6]);
                    suppliers.add(distributor);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    public void writeInCsv(String filePath, String[] data) {
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            if (!file.exists())
                file.createNewFile();

            PrintWriter outputfile = new PrintWriter(new FileWriter(file, true));
            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext(data);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createNewFile(String filePath) {
        try {
            Writer fileWriter = new FileWriter(filePath, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
