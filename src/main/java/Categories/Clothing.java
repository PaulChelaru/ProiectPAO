package Categories;

import Products.Product;
import java.util.Map;

public class Clothing extends Category{
    public Clothing() {}

    public Clothing(Map<Product, Integer> products) {
        super(products);
    }

    public Clothing(Product product, int quantity) {
        super(product, quantity);
    }
}
