package pl.wbubula.ecommerce.catalog.sales.cart;

public class CartLine {
    private final String productId;
    private final Integer qty;

    public CartLine(String productId, Integer qty) {
        this.productId = productId;
        this.qty = qty;
    }

    public String getProductId() {
        return productId;
    }

    public int getQty() {
        return qty;
    }
}
