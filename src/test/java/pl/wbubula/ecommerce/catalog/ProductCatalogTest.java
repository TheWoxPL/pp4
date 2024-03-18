package pl.wbubula.ecommerce.catalog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProductCatalogTest {

    @Test
    void itListAvailableProducts(){
        ProductCatalog catalog = new ProductCatalog();

        List<Product> products = catalog.allProducts();

        assert products.isEmpty();
    }

    @Test
    void itAllowsToAddProduct(){
        ProductCatalog catalog = new ProductCatalog();

       catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(100));
       List<Product> products = catalog.allProducts();

        assertThat(products)
                .hasSize(1);
    }

    @Test
    void itLoadsSingleProductById(){
        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(100));

        Product loaded = catalog.getProductById(id);
        assertThat(id).isEqualTo(loaded.getId());
    }

    @Test
    void itAllowsChangePrice(){
        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(100));

        catalog.changePrice(id, BigDecimal.valueOf(10.10));
        Product loaded = catalog.getProductById(id);

        assertThat(BigDecimal.valueOf(10.10)).isEqualTo(loaded.getPrice());
    }

}
