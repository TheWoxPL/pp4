package pl.wbubula.ecommerce.catalog.sales;

import org.apache.catalina.Store;
import pl.wbubula.ecommerce.catalog.sales.cart.Cart;
import pl.wbubula.ecommerce.catalog.sales.cart.InMemoryCartStorage;
import pl.wbubula.ecommerce.catalog.sales.offer.AcceptOfferRequest;
import pl.wbubula.ecommerce.catalog.sales.offer.Offer;
import pl.wbubula.ecommerce.catalog.sales.offer.OfferCalculator;
import pl.wbubula.ecommerce.catalog.sales.reservation.Reservation;
import pl.wbubula.ecommerce.catalog.sales.reservation.ReservationDetails;
import pl.wbubula.ecommerce.catalog.sales.reservation.ReservationRepository;

import java.util.UUID;

public class SalesFacade {
    private InMemoryCartStorage cartStorage;
    private OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private ReservationRepository reservationRepository;

    public SalesFacade(InMemoryCartStorage cartStorage, OfferCalculator offerCalculator, PaymentGateway paymentGateway, ReservationRepository reservationRepository) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservationRepository = reservationRepository;
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = cartStorage.findByCustomerId(customerId)
                .orElse(Cart.empty());
        return offerCalculator.calculate(cart.getLines());
    }

    public ReservationDetails acceptOffer(String customerId) {
        return new ReservationDetails();
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.addProduct(productId);
    }

    private Cart loadCartForCustomer(String customerId) {
        return cartStorage.findByCustomerId(customerId)
                .orElse(Cart.empty());
    }

    public ReservationDetails acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest){
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                RegisterPaymentRequest.of(reservationId, acceptOfferRequest, offer.getTotal())
        );
        Reservation reservation = Reservation.of(
                reservationId,
                customerId,
                acceptOfferRequest,
                offer,
                paymentDetails
        );

        reservationRepository.add(reservation);

        return new ReservationDetails(reservationId, paymentDetails.getPaymentURL());
    }

}
