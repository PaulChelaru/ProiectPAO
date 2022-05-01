package Categories;

import Products.Product;
import java.util.Map;

public class Accessories extends Category{
    public Accessories() {}

    public Accessories(Map<Product, Integer> products) {
        super(products);
    }

    public  Accessories(Product product, int quantity) {
        super(product, quantity);
    }
}
