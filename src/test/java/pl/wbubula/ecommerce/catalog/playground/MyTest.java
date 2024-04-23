package pl.wbubula.ecommerce.catalog.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import pl.wbubula.ecommerce.catalog.ProductStorageRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MyTest {

    @Autowired
    JdbcTemplate jdbcTemplate;
    ProductStorageRepository productStorageRepository = new ProductStorageRepository(jdbcTemplate);

    @Autowired
    public MyTest(ProductStorageRepository productStorageRepository){
        this.productStorageRepository = productStorageRepository;
    }

    @Test
    void test(){
        List str = productStorageRepository.getString();
        System.out.println("test");
        System.out.println(str.getClass());
    }

    @Test
    void itSelectForCurrentDate() {
        var myDate=jdbcTemplate.queryForObject(
                "SElect now() myCurrentDate",
                String.class
        );

        assertThat(myDate).contains("2024");
    }
}
