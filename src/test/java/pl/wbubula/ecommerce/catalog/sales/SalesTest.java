package pl.wbubula.ecommerce.catalog.sales;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import pl.wbubula.ecommerce.catalog.sales.Offer;

public class SalesTest {
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
        return new SalesFacade();
    }

    @Test
    void idAllowsAddToCart(){

    }

    @Test
    void itAllowsAcceptOffer(){

    }
}
