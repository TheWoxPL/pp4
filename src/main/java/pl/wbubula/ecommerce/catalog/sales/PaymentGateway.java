package pl.wbubula.ecommerce.catalog.sales;

public interface PaymentGateway {
    PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest);
}
