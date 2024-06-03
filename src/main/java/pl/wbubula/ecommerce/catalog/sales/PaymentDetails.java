package pl.wbubula.ecommerce.catalog.sales;

public class PaymentDetails {
    private final String url;

    public PaymentDetails(String url) {

        this.url = url;
    }

    public String getPaymentURL() {
        return url;
    }
}
