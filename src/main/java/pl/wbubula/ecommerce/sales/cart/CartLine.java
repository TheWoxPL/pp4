package pl.wbubula.ecommerce.sales.cart;

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
