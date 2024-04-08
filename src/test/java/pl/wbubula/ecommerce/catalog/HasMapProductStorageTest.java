package pl.wbubula.ecommerce.catalog;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class HasMapProductStorageTest {


    private static final Object TEST_PRODUCT_NAME = "test product";

    @Test
    void isStoreNewProduct(){
        ProductStorage storage = thereIsProductStorage();
        Product product = thereIsExampleProduct();
        storage.add(product);
        List<Product> products = storage.getAllProducts();
        assertThat(products)
                .hasSize(1)
                .extracting(Product::getName)
                .contains((String) TEST_PRODUCT_NAME);
    }

    private ProductStorage thereIsProductStorage() {
        return new HasMapProductStorage();
    }

    private Product thereIsExampleProduct() {
        return new Product(UUID.randomUUID(), "", "", BigDecimal.valueOf(100));
    }



    @Test
    void itLoadsAllProducts(){

    }

    @Test
    void itLoadsProductById(){

    }
}
