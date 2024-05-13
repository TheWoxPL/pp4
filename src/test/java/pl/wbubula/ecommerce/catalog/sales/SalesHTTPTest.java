package pl.wbubula.ecommerce.catalog.sales;

import com.sun.net.httpserver.HttpsServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import pl.wbubula.ecommerce.catalog.ProductCatalog;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesHTTPTest {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate http;

    @Autowired
    ProductCatalog catalog;

    @Test
    void itAcceptsOfferHappyPath(){
        var productId = thereIsExampleProduct("Example product", BigDecimal.valueOf(10));
        //ACT
        //add to car
        var uri = String.format("api/add-to-cart/%s", productId);
        var addProductToCartUrl = String.format("http://localhost:%s/%s", port, uri);

        http.postForEntity(addProductToCartUrl, null, Object.class);

        //ACT
        //accept offer
        AcceptOfferRequest acceptOfferRequest = new AcceptOfferRequest();
        acceptOfferRequest
                .setFirstname("Jakub")
                .setLastname("asd")
                .setEmail("asd");

        var acceptOfferUrl = String.format("http://localhost:%s/%s", port, "api/accept-offer");
        ResponseEntity<ReservationDetails> reservationResponse = http.postForEntity(acceptOfferUrl, acceptOfferRequest, ReservationDetails.class);
        assertEquals(HttpStatus.OK, reservationResponse.getStatusCode());
        assertEquals(BigDecimal.valueOf(10), reservationResponse.getBody().getTotal());
        assertNotNull(reservationResponse.getBody().getReservationId());
        assertNotNull(reservationResponse.getBody().getPaymentURL());
    }

    private Object thereIsExampleProduct(String name, BigDecimal price) {
        var prodId = catalog.addProduct(name, "desc", price);
        return prodId;
    }
}
