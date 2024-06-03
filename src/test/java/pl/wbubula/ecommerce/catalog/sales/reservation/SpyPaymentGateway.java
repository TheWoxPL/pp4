package pl.wbubula.ecommerce.catalog.sales.reservation;

import pl.wbubula.ecommerce.sales.payment.PaymentDetails;
import pl.wbubula.ecommerce.sales.payment.PaymentGateway;
import pl.wbubula.ecommerce.sales.payment.RegisterPaymentRequest;

public class SpyPaymentGateway implements PaymentGateway {
    Integer requestCount = 0;
    public Integer getRequestsCount() {
        return requestCount;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        this.requestCount++;
        return new PaymentDetails("http://spy-gateway");
    }
}
