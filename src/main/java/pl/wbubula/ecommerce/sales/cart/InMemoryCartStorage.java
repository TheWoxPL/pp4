package pl.wbubula.ecommerce.sales.cart;

import java.util.Map;
import java.util.Optional;

public class InMemoryCartStorage {
    Map<String, Cart> carts;
    public Optional<Cart> findByCustomerId(String customerId) {
        return Optional.empty();
    }

    public void save(String customerId, Cart cart) {
        carts.put(customerId, cart);
    }

}
