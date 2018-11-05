package ua.edu.ukma.gpd.storage.entity;

public class OrderProduct {

    private Long orderId;

    private Long productId;

    private Integer amount;

    private Integer amountReturned;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(Integer amountReturned) {
        this.amountReturned = amountReturned;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", amount=" + amount +
                ", amountReturned=" + amountReturned +
                '}';
    }
}
