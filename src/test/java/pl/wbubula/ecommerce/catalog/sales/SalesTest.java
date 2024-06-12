package pl.wbubula.ecommerce.catalog.sales;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wbubula.ecommerce.catalog.SqlProductStorage;
import pl.wbubula.ecommerce.sales.SalesFacade;
import pl.wbubula.ecommerce.sales.cart.InMemoryCartStorage;
import pl.wbubula.ecommerce.sales.offer.Offer;
import pl.wbubula.ecommerce.sales.offer.OfferCalculator;
import pl.wbubula.ecommerce.sales.reservation.ReservationRepository;
import pl.wbubula.ecommerce.catalog.sales.reservation.SpyPaymentGateway;

@SpringBootTest
public class SalesTest {

    @Autowired
    SqlProductStorage sqlProductStorage;

    @Test
    void itShowsOffers(){
        SalesFacade sales = thereIsSaleFacade();
        String customerId = thereIsExampleCustomer("Kuba");

        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(0, offer.getItemsCount());
        assertEquals(BigDecimal.ZERO, offer.getTotal());
    }

    private String thereIsExampleCustomer(String id) {
        return id;
    }

    private SalesFacade thereIsSaleFacade() {
        return new SalesFacade(
                new InMemoryCartStorage(),
                new OfferCalculator(sqlProductStorage),
                new SpyPaymentGateway(),
                new ReservationRepository()
        );
    }

    @Test
    void idAllowsAddToCart(){
        String productId = thereIsProduct("Example", "desc", BigDecimal.valueOf(10));
        String customerId = thereIsExampleCustomer("Wojtek");
        SalesFacade sales = thereIsSaleFacade();

        sales.addToCart(customerId, productId);
        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(1, offer.getItemsCount());
        assertEquals(BigDecimal.valueOf(10), offer.getTotal());
    }

    @Test
    void idAllowsAddToMultipleProductsCart(){
        String productA = thereIsProduct("Example", "desc", BigDecimal.valueOf(10));
        String productB = thereIsProduct("Example", "desc", BigDecimal.valueOf(20));
        String customerId = thereIsExampleCustomer("Wojtek");
        SalesFacade sales = thereIsSaleFacade();

        sales.addToCart(customerId, productA);
        sales.addToCart(customerId, productB);
        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(2, offer.getItemsCount());
        assertEquals(BigDecimal.valueOf(30), offer.getTotal());
    }

    @Test
    void itDistinguishCartsByCustomer(){
        String productA = thereIsProduct("Example", "desc", BigDecimal.valueOf(10));
        String productB = thereIsProduct("Example", "desc", BigDecimal.valueOf(20));
        String customerA = thereIsExampleCustomer("Wojtek");
        String customerB = thereIsExampleCustomer("Lukasz");
        SalesFacade sales = thereIsSaleFacade();

        sales.addToCart(customerA, productA);
        sales.addToCart(customerB, productB);
        Offer offerA = sales.getCurrentOffer(customerA);
        Offer offerB = sales.getCurrentOffer(customerB);

        assertEquals(1, offerA.getItemsCount());
        assertEquals(BigDecimal.valueOf(10), offerA.getTotal());

        assertEquals(1, offerA.getItemsCount());
        assertEquals(BigDecimal.valueOf(20), offerA.getTotal());
    }

    private String thereIsProduct(String example, String desc, BigDecimal bigDecimal) {
        return "123ab";
    }

    @Test
    void itAllowsAcceptOffer(){
//        String productId = thereIsProduct("Example", "desc", BigDecimal.valueOf(10));
//        String customerId = thereIsExampleCustomer("Wojtek");
//        SalesFacade sales = thereIsSaleFacade();
//
//        sales.addToCart(customerId, productId);
//        Offer offer = sales.getCurrentOffer(customerId);
//
//        assertEquals(1, offer.getItemsCount());
//        assertEquals(BigDecimal.valueOf(10), offer.getTotal());
    }
}
