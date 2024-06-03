package pl.wbubula.ecommerce.sales.cart;

import java.util.Optional;

public class InMemoryCartStorage {

    public Optional<Cart> findByCustomerId(String customerId) {
        return Optional.empty();
    }
}
