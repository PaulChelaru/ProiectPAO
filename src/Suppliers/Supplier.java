package Suppliers;

import Address.Address;
import java.util.Scanner;

public class Supplier {
        private String name;
        private Address address;
        private String phoneNumber;

        public Supplier() {}

        public Supplier(String name, Address address, String phoneNumber) {
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return("Supplier's name: " + this.name + " " + "Supplier's address: " + this.address + " " + "Supplier's mobile: " + this.phoneNumber);
        }

        public static Supplier addSupplier() {
            Scanner in =new Scanner(System.in);
            System.out.println("Add Supplier\n");
            System.out.println("Supplier name: ");
            String name = in.next();
            System.out.println("Phone number: ");
            String phoneNumber = in.next();
            System.out.println("Supplier address\n");
            Address address = Address.addAddress();

            return new Supplier(name, address, phoneNumber);
        }
}
