package Address;

import java.util.Scanner;

public class Address {
    private String street;
    private String city;
    private String country;
    private String postcode;

    public Address(String street, String city, String country, String postcode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

//    @Override
//    public String toString() {
//        return("Street: " + this.street + "\n" + "City:  " + this.city + "\n" + "Country: " + this.country + "\n" + "Postcode: " + this.postcode);
//    }


    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }

    public static Address addAddress() {
         Scanner in=new Scanner(System.in);
         System.out.println("Add Address\n");
         System.out.println("Street: ");
         String street = in.next();
         System.out.println("City: ");
         String city = in.next();
         System.out.println("Countruy: ");
         String country = in.next();
         System.out.println("Postcode: ");
         String postcode = in.next();

         return new Address(street, city, country, postcode);
     }
}
