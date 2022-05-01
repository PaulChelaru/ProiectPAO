package Categories;

import Products.Product;

import java.util.HashMap;
import java.util.Map;

public class Category {
    private Map<Product, Integer> products = new HashMap<>();

    public Category() {}

    public Category(Map<Product, Integer> products) {
        this.products = products;
    }

    public Category(Product product, int quantity) {
        this.products.put(product, quantity);
    }

    public void addProduct(Product product, int quantity){
        products.put(product, quantity);
    }

    public void removeProduct(String productName) {
        products.keySet().removeIf(key -> key.getName().equals(productName));
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public boolean searchProduct(String productName) {
        for (Map.Entry<Product, Integer> product : products.entrySet())
            return product.getKey().getName().equals(productName);
        return false;
    }

    public void modifyPrice(String productName, int newPrice) {
        for (Map.Entry<Product, Integer> product : products.entrySet())
            if (product.getKey().getName().equals(productName))
                product.getKey().setPrice(newPrice);
    }
    public void increasePrice(int percentage) {
        for (Map.Entry<Product, Integer> product : products.entrySet())
            product.getKey().setPrice(product.getKey().getPrice() * (100 + percentage) / 100);
    }

    public void decreasePrice(int percentage) {
        for (Map.Entry<Product, Integer> product : products.entrySet())
            product.getKey().setPrice(product.getKey().getPrice() * (100 - percentage) / 100);
    }

    public Product cheapestProduct() {
        Product result = null;
        int price = Integer.MAX_VALUE;
        for (Map.Entry<Product, Integer> product : products.entrySet())
            if(product.getKey().getPrice() < price) {
                result = product.getKey();
                price = product.getKey().getPrice();
            }
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "products=" + products +
                '}';
    }
}
