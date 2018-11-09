package ua.edu.ukma.gpd.storage.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrderProductDto {

    @NotNull
    private Long orderId;

    @NotNull
    private Long productId;

    @NotBlank(message = "Enter amount of product")
    @Range(min = 1, message = "Enter correct amount")
    private Integer amount;

    @Range(min = 1, message = "Enter amount of products, U wanna return")
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
}
