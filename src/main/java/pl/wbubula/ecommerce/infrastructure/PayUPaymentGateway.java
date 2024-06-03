package pl.wbubula.ecommerce.infrastructure;

import pl.wbubula.ecommerce.sales.payment.PaymentDetails;
import pl.wbubula.ecommerce.sales.payment.PaymentGateway;
import pl.wbubula.ecommerce.sales.payment.RegisterPaymentRequest;

public class PayUPaymentGateway implements PaymentGateway {
    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        return null;
    }
}
