package pl.wbubula.ecommerce.catalog.sales.reservation;

import java.math.BigDecimal;

public class ReservationDetails {

    private String reservationId;
    private String paymentURL;

    public ReservationDetails(String reservationId, String paymentURL) {
        this.reservationId = reservationId;
        this.paymentURL = paymentURL;
    }

    public ReservationDetails() {

    }

    public BigDecimal getTotal() {
        return BigDecimal.ZERO;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getPaymentURL() {
        return "http://example-payment-website";
    }
}
