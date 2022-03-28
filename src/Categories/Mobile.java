package Categories;

import Products.Product;
import java.util.Map;

public class Mobile extends Category {
    public Mobile(Map<Product, Integer> products) {
        super(products);
    }

    public Mobile(Product product, int quantity) {
        super(product, quantity);
    }

    public Mobile() {}
}