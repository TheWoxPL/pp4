package pl.wbubula.ecommerce.catalog;




import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class HttpProductCatalogTest {

    ProductCatalog catalog;

    @LocalServerPort
    private int localPort;

    @Autowired
    TestRestTemplate http;

    @Test
    void itLoadsProducts(){
        var url = String.format("http://localhost:%s/%s",
                localPort,
                ""
        );
        catalog.addProduct("Example product", "asd", BigDecimal.valueOf(10));

        ResponseEntity<Product[]> response = http.getForEntity(url, Product[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .hasSizeGreaterThan(0)
                .extracting("name")
                .contains("Example product");
    }

    @Test
    void itLoadsHomePage(){
        var url = String.format("http://localhost:%s/%s",
                localPort,
                ""
                );
        ResponseEntity<String> response = http.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Welcome");
    }

}
