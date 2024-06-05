package pl.wbubula.ecommerce.sales.offer;

import java.math.BigDecimal;

public class Offer {
    private BigDecimal total;
    private int itemsCount;

    public Offer(BigDecimal total, int itemsCount) {
        this.total = total;
        this.itemsCount = itemsCount;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
