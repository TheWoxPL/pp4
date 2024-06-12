package pl.wbubula.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import pl.wbubula.ecommerce.catalog.ProductCatalog;
import pl.wbubula.ecommerce.catalog.SqlProductStorage;
import pl.wbubula.ecommerce.payu.PayU;
import pl.wbubula.ecommerce.payu.PayUCredentials;
import pl.wbubula.ecommerce.sales.SalesFacade;
import pl.wbubula.ecommerce.sales.cart.InMemoryCartStorage;
import pl.wbubula.ecommerce.sales.offer.OfferCalculator;
import pl.wbubula.ecommerce.sales.payment.PaymentGateway;
import pl.wbubula.ecommerce.sales.reservation.ReservationRepository;

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
        catalog.addProduct("Lego set 8083" , "Nice one", BigDecimal.valueOf(10));
        catalog.addProduct("Cobi Blocks" , "Nice one" , BigDecimal.valueOf(5));
        return catalog;
    }

    @Bean
    PaymentGateway createPaymentGateway(){
        return new PayU(
                new RestTemplate(),
                PayUCredentials.sandbox(
                        "300746",
                        "2ee86a66e5d97e3fadc400c9f19b065d"
                )
        );

    }

    @Bean
    SalesFacade createSales(){
        return new SalesFacade(
                new InMemoryCartStorage(),
                new OfferCalculator(sqlProductStorage),
                createPaymentGateway(),
                new ReservationRepository()
        );
    }
}