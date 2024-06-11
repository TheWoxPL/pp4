package pl.wbubula.ecommerce.infrastructure;

import pl.wbubula.ecommerce.payu.*;
import pl.wbubula.ecommerce.sales.payment.PaymentDetails;
import pl.wbubula.ecommerce.sales.payment.PaymentGateway;
import pl.wbubula.ecommerce.sales.payment.RegisterPaymentRequest;

import java.util.Arrays;
import java.util.UUID;

public class PayUPaymentGateway implements PaymentGateway {

    PayU payU;

    public PayUPaymentGateway(PayU payU) {
        this.payU = payU;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {

        var request = new OrderCreateRequest();
        request
                .setNotifyUrl("https://my.example.shop.wbub.pl/api/order")
                .setCustomerIp("127.0.0.1")
                .setMerchantPosId("300746")
                .setDescription("My ebook")
                .setCurrencyCode("PLN")
                .setTotalAmount(registerPaymentRequest.getTotalAsPennies())
                .setExtOrderId(UUID.randomUUID().toString())
                .setBuyer((new Buyer())
                        .setEmail(registerPaymentRequest.getEmail())
                        .setFirstName(registerPaymentRequest.getFirstname())
                        .setLastName(registerPaymentRequest.getLastname())
                        .setLanguage("pl")
                )
                .setProducts(Arrays.asList(
                        new Product()
                                .setName("Product X")
                                .setQuantity(1)
                                .setUnitPrice(210000)
                ));

        OrderCreateResponse response = payU.handle(request);


        return null;
    }
}
