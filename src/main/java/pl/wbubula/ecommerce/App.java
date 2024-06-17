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
    public static void main(String[] args){
        System.out.println("Here we go!!!");
        SpringApplication.run(App.class,args);
    }

    @Bean
    public SqlProductStorage sqlProductStorage() {
        return new SqlProductStorage();
    }

    @Bean
    ProductCatalog createMyProductCatalog(SqlProductStorage sqlProductStorage) {
        var catalog = new ProductCatalog(sqlProductStorage);
        catalog.setUpDatabase();

        catalog.addProduct("Cobi Blocks", "Klocki konstrukcyjne, zestaw 250 elementów", BigDecimal.valueOf(10));
        catalog.addProduct("LEGO City", "Zestaw Policja, 300 elementów", BigDecimal.valueOf(5));
        catalog.addProduct("Mattel Barbie", "Lalka Barbie z akcesoriami", BigDecimal.valueOf(20));
        catalog.addProduct("Hot Wheels Track", "Tor wyścigowy z samochodzikami", BigDecimal.valueOf(49.99));
        catalog.addProduct("Nerf N-Strike", "Wyrzutnia Nerf z 10 strzałkami", BigDecimal.valueOf(89.99));
        catalog.addProduct("Play-Doh", "Zestaw ciastolin z formami", BigDecimal.valueOf(29.99));
        catalog.addProduct("Hasbro Monopoly", "Gra planszowa Monopoly Classic", BigDecimal.valueOf(119.99));
        catalog.addProduct("Fisher-Price", "Interaktywny stolik edukacyjny", BigDecimal.valueOf(149.99));
        catalog.addProduct("Spin Master Paw Patrol", "Figurki Psiego Patrolu, zestaw 6 szt.", BigDecimal.valueOf(69.99));
        catalog.addProduct("Disney Puzzle", "Puzzle 1000 elementów, motyw Disney", BigDecimal.valueOf(39.99));
        catalog.addProduct("Crayola", "Zestaw kredek i markerów, 50 szt.", BigDecimal.valueOf(19.99));
        catalog.addProduct("Jenga", "Gra zręcznościowa Jenga Classic", BigDecimal.valueOf(59.99));
        catalog.addProduct("UNO", "Gra karciana UNO", BigDecimal.valueOf(14.99));
        catalog.addProduct("TREFL Puzzle", "Puzzle 500 elementów, krajobraz", BigDecimal.valueOf(24.99));
        catalog.addProduct("Mega Bloks", "Klocki konstrukcyjne dla dzieci, zestaw 100 elementów", BigDecimal.valueOf(49.99));
        catalog.addProduct("Ravensburger", "Gra logiczna Labirynt", BigDecimal.valueOf(89.99));
        catalog.addProduct("Bruder", "Model traktora z akcesoriami", BigDecimal.valueOf(129.99));
        catalog.addProduct("Hape", "Drewniana kuchnia dla dzieci z akcesoriami", BigDecimal.valueOf(199.99));
        catalog.addProduct("VTech", "Interaktywny smartfon dla dzieci", BigDecimal.valueOf(34.99));
        catalog.addProduct("Dickie Toys", "Radiowo sterowany samochód wyścigowy", BigDecimal.valueOf(79.99));

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
    SalesFacade createSales(ProductCatalog catalog){
        return new SalesFacade(
                new InMemoryCartStorage(),
                new OfferCalculator(catalog),
                createPaymentGateway(),
                new ReservationRepository()
        );
    }
}