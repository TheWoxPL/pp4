package pl.wbubula.ecommerce.sales.payment;

public class PaymentDetails {
    private final String url;
    private final String id;

    public PaymentDetails(String url, String id) {
        this.url = url;
        this.id = id;
    }

    public String getPaymentURL() {
        return url;
    }
}
