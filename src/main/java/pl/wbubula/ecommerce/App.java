package pl.wbubula.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.wbubula.ecommerce.catalog.ProductCatalog;
import pl.wbubula.ecommerce.catalog.SqlProductStorage;
import pl.wbubula.ecommerce.sales.SalesFacade;
import pl.wbubula.ecommerce.sales.cart.InMemoryCartStorage;
import pl.wbubula.ecommerce.sales.offer.OfferCalculator;
import pl.wbubula.ecommerce.sales.reservation.ReservationRepository;
import pl.wbubula.ecommerce.infrastructure.PayUPaymentGateway;

import java.math.BigDecimal;

@SpringBootApplication
public class App {
    @Autowired
    SqlProductStorage sqlProductStorage;

    public static void main(String[] args){
        System.out.println("Here we go!!!");
        SpringApplication.run(App.class,args);
    }
    @Bean
    ProductCatalog createMyProductCatalog() {
        var catalog = new ProductCatalog(sqlProductStorage);
        catalog.setUpDatabase();
        catalog.addProduct("Lego set 8083" , "Nice one", BigDecimal.valueOf(100));
        catalog.addProduct("Cobi Blocks" , "Nice one" , BigDecimal.valueOf(140));
        return catalog;
    }

    @Bean
    SalesFacade createSales(){
        return new SalesFacade(
                new InMemoryCartStorage(),
                new OfferCalculator(),
                new PayUPaymentGateway(),
                new ReservationRepository()
        );
    }
}