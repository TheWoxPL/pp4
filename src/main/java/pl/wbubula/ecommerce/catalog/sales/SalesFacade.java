package pl.wbubula.ecommerce.catalog.sales;

import pl.wbubula.ecommerce.catalog.sales.cart.Cart;
import pl.wbubula.ecommerce.catalog.sales.cart.InMemoryCartStorage;
import pl.wbubula.ecommerce.catalog.sales.offer.Offer;
import pl.wbubula.ecommerce.catalog.sales.offer.OfferCalculator;
import pl.wbubula.ecommerce.catalog.sales.order.ReservationDetails;

public class SalesFacade {
    private InMemoryCartStorage cartStorage;
    private OfferCalculator offertCalculator;

    public SalesFacade(InMemoryCartStorage cartStorage, OfferCalculator offertCalculator) {
        this.cartStorage = cartStorage;
        this.offertCalculator = offertCalculator;
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = loadCartForCustomer(customerId);
        return  offertCalculator.calculate(cart.getLines());
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
}
