package pl.wbubula.ecommerce.sales.payment;

public class PaymentDetails {
    private final String url;

    public PaymentDetails(String url) {

        this.url = url;
    }

    public String getPaymentURL() {
        return url;
    }
}
