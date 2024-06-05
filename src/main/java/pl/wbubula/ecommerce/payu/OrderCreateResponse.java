package pl.wbubula.ecommerce.payu;

public class OrderCreateResponse {
//    {
//        "status": {
//        "statusCode": "SUCCESS"
//    },
//        "redirectUri": "{payment_summary_redirection_url}",
//            "orderId": "WZHF5FFDRJ140731GUEST000P01",
//            "extOrderId": "{YOUR_EXT_ORDER_ID}"
//    }

    String extOrderId;
    Status status;
    String redirectUri;
    String orderId;

    public String getExtOrderId() {
        return extOrderId;
    }

    public OrderCreateResponse setExtOrderId(String extOrderId) {
        this.extOrderId = extOrderId;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public OrderCreateResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public OrderCreateResponse setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    public OrderCreateResponse setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getOrderId() {
        return orderId;
    }

}
