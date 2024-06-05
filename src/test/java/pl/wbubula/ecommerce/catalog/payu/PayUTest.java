package pl.wbubula.ecommerce.catalog.payu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import pl.wbubula.ecommerce.payu.*;

import java.util.Arrays;
import java.util.UUID;

@SpringBootTest
public class PayUTest {

    @Test
    void creatingNewPayment(){
        PayU payu = thereIsPayU();
        OrderCreateRequest orderCreateRequest = createExampleOrderRequest();

        OrderCreateResponse response = payu.handle(orderCreateRequest);

        assertNotNull(response.getRedirectUri());
        assertNotNull(response.getOrderId());
    }

    private OrderCreateRequest createExampleOrderRequest() {
        var createRequest = new OrderCreateRequest();
            createRequest
                    .setNotifyUrl("https://my.example.shop.wbub.pl/api/order")
                    .setCustomerIp("127.0.0.1")
                    .setMerchantPosId("300746")
                    .setDescription("My ebook")
                    .setCurrencyCode("PLN")
                    .setTotalAmount(21000)
                    .setExtOrderId(UUID.randomUUID().toString())
                    .setBuyer((new Buyer())
                            .setEmail("john.doe@example.com")
                            .setFirstName("John")
                            .setLastName("Doe")
                            .setLanguage("pl")
                    )
                    .setProducts(Arrays.asList(
                            new Product()
                                    .setName("Product X")
                                    .setQuantity(1)
                                    .setUnitPrice(210000)
                    ));

        return createRequest;
    }

    private PayU thereIsPayU() {
        return new PayU(
                new RestTemplate(),
                PayUCredentials.sandbox(
                        "300746",
                        "2ee86a66e5d97e3fadc400c9f19b065d"
                )
        );
    }

}
