package pl.wbubula.ecommerce.catalog;

import java.util.List;

public interface ProductStorage {
    void add(Product newProduct);
    void setUpDatabase();
    Product getProductById(String id);

    List<Product> getAllProducts();
}
