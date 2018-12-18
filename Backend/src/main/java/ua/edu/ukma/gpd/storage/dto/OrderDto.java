package ua.edu.ukma.gpd.storage.dto;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

public class OrderDto {

    private String annotation;

    @NotNull()
    private Integer orderType;

    @NotNull()
    private Long createdBy;

    // id and amount of product
    @NotNull()
    private HashMap<Long, Integer> products;

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public HashMap<Long, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Long, Integer> products) {
        this.products = products;
    }
}
