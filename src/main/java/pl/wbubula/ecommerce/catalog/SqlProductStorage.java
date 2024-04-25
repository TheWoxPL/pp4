package pl.wbubula.ecommerce.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class SqlProductStorage implements ProductStorage{
    private JdbcTemplate jdbcTemplate;

    public SqlProductStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public void setUpDatabase(){
        jdbcTemplate.execute("DROP TABLE `product_catalog__products` IF EXISTS;");

        var createTableSql = """
            CREATE TABLE `product_catalog__products` (
                `id` VARCHAR(255) NOT NULL,
                `name` VARCHAR(100) NOT NULL,
                `description` VARCHAR(512) NOT NULL,
                `price` DECIMAL(12,2),
                PRIMARY KEY(id)
            );
            
        """;
        jdbcTemplate.execute(createTableSql);

    }
    
    @Override
    public void add(Product newProduct) {
        var myInsertSql = String.format("""
            INSERT INTO `product_catalog__products` (id, name, description, price)
            VALUES
                ('%s', '%s', '%s', %.2f)
            ;
        """,newProduct.getId(), newProduct.getName(), newProduct.getDescription(), newProduct.getPrice());
        jdbcTemplate.execute(myInsertSql);
    }

    @Override
    public Product getProductById(String id) {
        var selectProductSql = "SELECt * from `product_catalog__products` where id = ?";
        Product loadedProduct = jdbcTemplate.queryForObject(
                selectProductSql,
                new Object[]{id},
                (rs, i) -> {
                    var myProduct = new Product(
                            UUID.fromString(rs.getString("id")),
                            rs.getString("name"),
                            rs.getString("name"),
                            BigDecimal.valueOf(0)
                    );
                    myProduct.changePrice(BigDecimal.valueOf((rs.getDouble("price"))));
                    return myProduct;
                }


        );
        return loadedProduct;

    }

    @Override
    public List<Product> getAllProducts() {
        var query="SELECT * FROM `product_catalog__products`";

        List<Product> products = jdbcTemplate.query(
                query,
                new BeanPropertyRowMapper<>(Product.class)
        );
        return products;
    }
    public void changePrice(String id, BigDecimal newPrice) {
        String query = "UPDATE `product_catalog__products` SET price = ? WHERE id = ?";
        jdbcTemplate.update(query, newPrice, id);

    }

}
