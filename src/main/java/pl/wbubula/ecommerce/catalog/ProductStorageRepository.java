package pl.wbubula.ecommerce.catalog;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductStorageRepository implements ProductStorage{


    private JdbcTemplate jdbcTemplate;

    public ProductStorageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Map<String, Object>> getString() {
        List<Map<String, Object>> list =  jdbcTemplate.queryForList("Select '1', '2';");
        return list;
    }

    @Override
    public void add(Product newProduct) {

    }

    @Override
    public Product getProductById(String id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }
}
