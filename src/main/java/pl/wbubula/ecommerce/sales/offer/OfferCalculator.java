package pl.wbubula.ecommerce.sales.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.wbubula.ecommerce.catalog.Product;
import pl.wbubula.ecommerce.catalog.ProductCatalog;
import pl.wbubula.ecommerce.catalog.SqlProductStorage;
import pl.wbubula.ecommerce.sales.cart.CartLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class OfferCalculator {
    @Autowired
    SqlProductStorage sqlProductStorage;

    public Offer calculate(List<CartLine> lines) {
        int quantitySum = 0;
        List<BigDecimal> finalPriceArray = new ArrayList<>();

        for (CartLine cartLine : lines) {
            quantitySum += cartLine.getQty();
            Product product = sqlProductStorage.getProductById(cartLine.getProductId());
            BigDecimal productPrice = product.getPrice();
            int nthForFree = 5;
            int quantity = cartLine.getQty() - cartLine.getQty() / nthForFree;
            BigDecimal lineTotal = BigDecimal.valueOf(quantity).multiply(productPrice);
            finalPriceArray.add(lineTotal);
        }

        BigDecimal finalPrice = finalPriceArray.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal finalPriceToReturn;
        if (finalPrice.compareTo(BigDecimal.valueOf(100)) >= 0) {
            finalPriceToReturn = finalPrice.multiply(BigDecimal.valueOf(0.9)); // 10% discount
        } else {
            finalPriceToReturn = finalPrice;
        }
        return new Offer(finalPriceToReturn, quantitySum);
    }
}
