package pl.wbubula.ecommerce.catalog;

import java.util.List;

public interface ProductStorage {
    void add(Product newProduct);

    Product getProductById(String id);

    List<Product> getAllProducts();
}
